package com0.dataengineeringgroup13.service.impl;

import com0.dataengineeringgroup13.common.AppContanst;
import com0.dataengineeringgroup13.contants.PaperColumnIndex;
import com0.dataengineeringgroup13.dto.ScientificPaper;
import com0.dataengineeringgroup13.service.ImportExcelAsyncService;
import com0.dataengineeringgroup13.service.ImportExcelService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import static com0.dataengineeringgroup13.common.AppContanst.EXCEL_IMPORT_NUMBER_OF_THREAD;

@Service
public class ImportExcelAsyncServiceImpl implements ImportExcelAsyncService {

    private static final Logger logger = LoggerFactory.getLogger(ImportExcelAsyncServiceImpl.class);

    @Value("${orientdb.connection.url}")
    private String connectionUrl;

    @Value("${orientdb.username}")
    private String username;

    @Value("${orientdb.password}")
    private String password;

    @Async("threadPoolExecutor")
    public CompletableFuture<Boolean> conductAsynImportExcelFile (XSSFWorkbook workbook, Integer rowFactor) throws SQLException {


        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        List<ScientificPaper> scientificPapers = new ArrayList<>();

        while (iterator.hasNext()) {

            Row row = iterator.next();
            int rowNum = row.getRowNum();
            System.out.println("==rowNum rowNum rowNum:" + rowNum);
            //System.out.println("== EXCEL_USER_LIST_HEADER_ROW_INDEX:" + AppContanst.EXCEL_USER_LIST_HEADER_ROW_INDEX);
            //System.out.println("==rowFactor.equals(rowNum%EXCEL_IMPORT_NUMBER_OF_THREAD:" + rowFactor.equals(rowNum%EXCEL_IMPORT_NUMBER_OF_THREAD));


            if (rowNum > AppContanst.EXCEL_USER_LIST_HEADER_ROW_INDEX && (rowFactor.equals(rowNum%EXCEL_IMPORT_NUMBER_OF_THREAD))) {
                readRow(scientificPapers, row);
            }

        }

        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    private void readRow(List<ScientificPaper> scientificPapers, Row row) throws SQLException {

        ScientificPaper scientificPaper = new ScientificPaper();
        readCell(scientificPaper, row);
        scientificPapers.add(scientificPaper);

        Properties info = new Properties();
        info.put("user", username);
        info.put("password", password);

        Connection conn = DriverManager.getConnection(connectionUrl, info);

        Statement stmt = conn.createStatement();

        //System.out.println("==excel subject:" + scientificPaper.getSubject());
        //System.out.println("==excel content:" + scientificPaper.getContent());

        stmt.executeQuery("INSERT INTO  PAPER(SUBJECT, CONTENT, AUTHOR, USER_IDENTIFIER) " +
                "            VALUES('"+scientificPaper.getSubject()+"', '"+scientificPaper.getContent()+"','"+scientificPaper.getAuthor()+"',''"+")");

    }

    private void readCell(ScientificPaper scientificPaper, Row row) {

        for (int i = 0; i < AppContanst.EXCEL_COLUMN_SIZE; i++) {

            Cell cell = row.getCell(i);
            //System.out.println("===row index getRowNum:" + row.getRowNum());

            if (cell != null) {
                switch (cell.getCellType()) {
                    case STRING:
                        logger.info(" cell:" + cell.getStringCellValue());
                        System.out.println("===cell:" + cell.getStringCellValue());
                        String value = cell.getStringCellValue();
                        value = value.replaceAll("[^A-Za-z0-9.,]","");

                        if (PaperColumnIndex.SUBJECT_PAPER.getColumnIndex().equals(cell.getColumnIndex())) {

                            scientificPaper.setSubject(value);
                        } else if (PaperColumnIndex.CONTENT_PAPER.getColumnIndex().equals(cell.getColumnIndex())) {

                            scientificPaper.setContent(value);
                        }
                        else if (PaperColumnIndex.AUTHOR_PAPER.getColumnIndex().equals(cell.getColumnIndex())) {

                            scientificPaper.setAuthor(value);
                        }



                        break;
                    case BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                    case NUMERIC:
                        System.out.println(cell.getNumericCellValue());
                        break;
                }
            }
            System.out.print(" - ");

        }
    }
}

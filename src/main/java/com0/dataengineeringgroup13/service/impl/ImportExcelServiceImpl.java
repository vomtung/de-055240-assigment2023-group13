package com0.dataengineeringgroup13.service.impl;

import com0.dataengineeringgroup13.common.AppContanst;
import com0.dataengineeringgroup13.contants.PaperColumnIndex;
import com0.dataengineeringgroup13.dto.ScientificPaper;
import com0.dataengineeringgroup13.service.ImportExcelAsyncService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com0.dataengineeringgroup13.service.ImportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com0.dataengineeringgroup13.common.AppContanst.EXCEL_IMPORT_NUMBER_OF_THREAD;

@Service
public class ImportExcelServiceImpl implements ImportExcelService {

    private static final Logger logger = LoggerFactory.getLogger(ImportExcelServiceImpl.class);

    @Autowired
    private Connection dataConnection;

    @Autowired
    private ImportExcelAsyncService importExcelAsyncService;

    public void importExcelFileAsync(XSSFWorkbook workbook) {

        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        CompletableFuture<Boolean> page1 = importExcelAsyncService.conductAsynImportExcelFile(workbook,0);
        CompletableFuture<Boolean> page2 = importExcelAsyncService.conductAsynImportExcelFile(workbook,1);
        CompletableFuture<Boolean> page3 = importExcelAsyncService.conductAsynImportExcelFile(workbook,2);
        CompletableFuture<Boolean> page4 = importExcelAsyncService.conductAsynImportExcelFile(workbook,3);
        CompletableFuture<Boolean> page5 = importExcelAsyncService.conductAsynImportExcelFile(workbook,4);

        // Wait until they are all done
        CompletableFuture.allOf(page1,page2,page3,page4,page5).join();

        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));

    }

    private void readRow(List<ScientificPaper> scientificPapers, Row row) {

        ScientificPaper scientificPaper = new ScientificPaper();
        readCell(scientificPaper, row);
        scientificPapers.add(scientificPaper);

    }

    private void readCell(ScientificPaper scientificPaper, Row row) {

        for (int i = 0; i < AppContanst.EXCEL_COLUMN_SIZE; i++) {

            Cell cell = row.getCell(i);

            if (cell != null) {
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.println(" cell:" + cell.getStringCellValue());
                        String value = cell.getStringCellValue();

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

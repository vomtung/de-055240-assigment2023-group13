package com0.dataengineeringgroup13.controller;


import com0.dataengineeringgroup13.common.AppContanst;
import com0.dataengineeringgroup13.dto.ScholarDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.ss.usermodel.CellType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@Controller
public class SettingController {

    @Value("${orientdb.connection.url}")
    private String connectionUrl;

    @Value("${orientdb.username}")
    private String username;

    @Value("${orientdb.password}")
    private String password;

    @GetMapping("/setting")
    public String index(Model model) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Properties info = new Properties();
        info.put("user", username);
        info.put("password", password);

        Connection conn = DriverManager.getConnection(connectionUrl, info);

        Statement stmt = conn.createStatement();
        List<ScholarDto> scholars = new ArrayList<ScholarDto>();

        //TODO
        //Create university vertex
        //select data similar example bellow
        /*ResultSet rs = stmt.executeQuery("SELECT * FROM ( SELECT EXPAND( OUT('SCHOLAR_USER') ) FROM SCHOLAR)"
                );


        while (rs.next()) {
            ScholarDto dto = new ScholarDto();
            dto.setUserId(rs.getLong("USR_ID"));
            dto.setFirstName(rs.getString("FIRST_NAME"));
            dto.setLastName(rs.getString("LAST_NAME"));
            scholars.add(dto);
        }*/

        String currentUser = null;
        if (auth != null && !auth.getName().equals("anonymousUser")) {
            currentUser = auth.getName();
        }

        model.addAttribute("scholarList", scholars);
        model.addAttribute("currentUser", currentUser);

        return "setting";
    }

    @PostMapping("setting/upload-user-excel-file")
    public String uploadExcelFile(Model model, MultipartFile file) throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {

            Row row = iterator.next();
            int rowNum = row.getRowNum();

            if (rowNum > AppContanst.EXCEL_USER_LIST_HEADER_ROW_INDEX) {
                readRow(row);
            }

            workbook.close();

        }
        return "success-result";
    }

    private void readRow(Row row) {

        for (int i = 0; i < AppContanst.EXCEL_COLUMN_SIZE; i++) {

            Cell cell = row.getCell(i);
            if (cell != null) {
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.println("cell:" + cell.getStringCellValue());
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

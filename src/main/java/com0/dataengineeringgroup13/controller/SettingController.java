package com0.dataengineeringgroup13.controller;


import com0.dataengineeringgroup13.dto.ScholarDto;
import com0.dataengineeringgroup13.service.ImportExcelService;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
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

    @Autowired
    private ImportExcelService importExcelAsyncService;

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

        importExcelAsyncService.importExcelFileAsync(workbook);

        workbook.close();

        return "success-result";
    }

    @GetMapping(value = "/paper-excel-template")
    public @ResponseBody HttpEntity<ByteArrayResource> getImage() throws IOException {
        InputStream in = getClass().getClassLoader()
                .getResourceAsStream("scientic_paper_teamplate.xlsx");

        byte[] excelContent = IOUtils.toByteArray(in);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "force-download"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=scientic_paper_teamplate.xlsx");

        return new HttpEntity<>(new ByteArrayResource(excelContent), header);

    }
}

package com0.dataengineeringgroup13.controller;


import com0.dataengineeringgroup13.dto.ScholarDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class UniversityController {

    @Value("${orientdb.connection.url}")
    private String connectionUrl;

    @Value("${orientdb.username}")
    private String username;

    @Value("${orientdb.password}")
    private String password;

    @GetMapping("/university")
    public String index(Model model) throws Exception{

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

        return "scholar";
    }
}

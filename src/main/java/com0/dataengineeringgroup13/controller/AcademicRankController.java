package com0.dataengineeringgroup13.controller;

import com.orientechnologies.orient.jdbc.OrientJdbcConnection;
import com0.dataengineeringgroup13.dto.AcademicRankDto;
import com0.dataengineeringgroup13.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.List;

@Controller
public class AcademicRankController {

    @Value("${orientdb.connection.url}")
    private String connectionUrl;

    @Value("${orientdb.username}")
    private String username;

    @Value("${orientdb.password}")
    private String password;

    @GetMapping("/academic-rank")
    public String index(Model model) throws Exception {

        Properties info = new Properties();
        info.put("user", username);
        info.put("password", password);

        Connection conn = DriverManager.getConnection(connectionUrl, info);

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT ACA_ID, RANK_NAME, ABBREVIATION FROM ACADEMIC_RANK");

        List<AcademicRankDto> ranks = new ArrayList<AcademicRankDto>();
        while (rs.next()) {
            AcademicRankDto dto = new AcademicRankDto();
            dto.setAcaId(rs.getLong("ACA_ID"));
            dto.setRankName(rs.getString("RANK_NAME"));
            dto.setAbbreviation(rs.getString("ABBREVIATION"));
            ranks.add(dto);
        }

        model.addAttribute("ranks", ranks);
        return "academic-rank";
    }
}

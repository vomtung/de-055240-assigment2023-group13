package com0.dataengineeringgroup13.controller;

import com.github.javafaker.Faker;
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
            dto.setAcaId(rs.getInt("ACA_ID"));
            dto.setRankName(rs.getString("RANK_NAME"));
            dto.setAbbreviation(rs.getString("ABBREVIATION"));
            ranks.add(dto);
        }

        model.addAttribute("ranks", ranks);
        return "academic-rank";
    }

    @GetMapping("/generate-rank")
    public String generateuser(Model model) throws Exception {

        Properties info = new Properties();
        info.put("user", username);
        info.put("password", password);

        Connection conn = DriverManager.getConnection(connectionUrl, info);
        Statement stmt = conn.createStatement();

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 1 + ",'Doctor of Philosophy', 'Ph.D')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 2 + ",'Assistant Professor', 'Asst. Prof')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 3 + ",'Lecturer', 'Lect')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 4 + ",'Instructor', 'Inst')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 5 + ",'Research Professor', 'Res. Prof')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 6 + ",'Adjunct Professor', 'Adj. Prof')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 7 + ",'Visiting Professor', 'Vis. Prof')"
        );

        return "generate-result";
    }

    @GetMapping("/truncate-academic-rank")
    public String truncateAcademicRank(Model model) throws Exception {

        Properties info = new Properties();
        info.put("user", username);
        info.put("password", password);

        Connection conn = DriverManager.getConnection(connectionUrl, info);
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("TRUNCATE CLASS ACADEMIC_RANK UNSAFE;"
        );

        return "generate-result";
    }
}

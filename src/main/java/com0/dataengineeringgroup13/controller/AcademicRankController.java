package com0.dataengineeringgroup13.controller;

import com0.dataengineeringgroup13.dto.AcademicRankDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

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

        String currentUser = null;
        if (auth != null && !auth.getName().equals("anonymousUser")) {
            currentUser = auth.getName();
        }

        model.addAttribute("ranks", ranks);
        model.addAttribute("currentUser", currentUser);

        return "academic-rank";
    }

    @GetMapping("/generate-rank")
    public String generateRank(Model model) throws Exception {

        Properties info = new Properties();
        info.put("user", username);
        info.put("password", password);

        Connection conn = DriverManager.getConnection(connectionUrl, info);
        Statement stmt = conn.createStatement();

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 1 + ",'Doctor of Philosophy', 'Ph.D')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 2 + ",'Doctor of Arts', 'DA')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 3 + ",'Doctor of Business Administration', 'DBA')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 4 + ",'Doctor of Canon Law', 'JCD')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 5 + ",'Doctor of Civil Law', 'DCL')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 6 + ",'Doctor of Education', 'EdD')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 7 + ",'Doctor of Medicine', 'MD')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 8 + ",'Doctor of Psychology', 'PsyD')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 9 + ",'Doctor of Science', 'DSC')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 10 + ",'Assistant Professor', 'Asst. Prof')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 11 + ",'Lecturer', 'Lect')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 12 + ",'Instructor', 'Inst')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 13 + ",'Research Professor', 'Res. Prof')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 14 + ",'Adjunct Professor', 'Adj. Prof')"
        );

        stmt.executeQuery("INSERT INTO    ACADEMIC_RANK(ACA_ID, RANK_NAME, ABBREVIATION) " +
                "            VALUES(" + 15 + ",'Visiting Professor', 'Vis. Prof')"
        );

        return "generate-result";
    }

    @GetMapping("/academic-rank/academic-rank")
    public String truncateAcademicRank(Model model) throws Exception {

        Properties info = new Properties();
        info.put("user", username);
        info.put("password", password);

        Connection conn = DriverManager.getConnection(connectionUrl, info);
        Statement stmt = conn.createStatement();

        stmt.executeQuery("TRUNCATE CLASS ACADEMIC_RANK UNSAFE;");

        return "generate-result";
    }
}

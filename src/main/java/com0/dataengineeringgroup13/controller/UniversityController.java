package com0.dataengineeringgroup13.controller;


import com0.dataengineeringgroup13.dto.ScholarDto;
import com0.dataengineeringgroup13.dto.UniversityDto;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Connection dataConnection;

    @GetMapping("/university")
    public String university(Model model) throws Exception{

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Statement stmt = dataConnection.createStatement();
        List<UniversityDto> universities = new ArrayList<UniversityDto>();

        //Dto: jv transfer object

        //TODO
        //Create university vertex
        //select data similar example bellow
        ResultSet rs = stmt.executeQuery("SELECT * FROM UNIVERSITY");
        
        while (rs.next()) {
            UniversityDto dto = new UniversityDto();
            // dto.setUniversityname(("UNIVERSITY_NAME"));
            // dto.setNation(("ABC"));
            dto.setUniversityname(rs.getString("UNIVERSITY_NAME"));
            dto.setNation(rs.getString("NATION"));
            //dto.setLastName(rs.getString("LAST_NAME"));
            universities.add(dto);
        }


        String currentUser = null;
        if (auth != null && !auth.getName().equals("anonymousUser")) {
            currentUser = auth.getName();
        }

        model.addAttribute("universityList", universities);
        model.addAttribute("currentUser", currentUser);

        return "university";
    }

    @GetMapping("/university/generate")
    public String generate(Model model) throws Exception{


        /*Statement stmt = dataConnection.createStatement();

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
        );*/

        return "generate-result";
    }
}
// Nation of university
// address of user (Province, district, street)
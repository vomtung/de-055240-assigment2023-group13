package com0.dataengineeringgroup13.service.impl;

import com0.dataengineeringgroup13.common.AppContanst;
import com0.dataengineeringgroup13.dto.PaperDto;
import com0.dataengineeringgroup13.service.ScientificPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Paper service
 *
 * @author Tung Vo
 * @since 22.OTC.2023
 */
@Service
public class ScientificPaperServiceImpl implements ScientificPaperService {

    @Autowired
    private Connection dataConnection;

    public void truncateScientificPaper() throws SQLException {
        Statement stmt = dataConnection.createStatement();

        stmt.executeQuery("TRUNCATE CLASS PAPER UNSAFE;");
    }

    @Override
    public List<PaperDto> findAll(Integer pageNumber) throws SQLException {

        List<PaperDto> paperDtos = new ArrayList<>();

        Statement stmt = dataConnection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM  PAPER SKIP " + AppContanst.NUMBER_RECORD_PER_PAGE * pageNumber  +
                "                 LIMIT "  + AppContanst.NUMBER_RECORD_PER_PAGE);

        while (rs.next()) {
            PaperDto paperDto = new PaperDto();
            paperDto.setSubject(rs.getString("SUBJECT"));
            paperDto.setContent(rs.getString("CONTENT"));
            paperDto.setAuthor(rs.getString("AUTHOR"));
            paperDto.setUserIdentifier(rs.getString("USER_IDENTIFIER"));

            paperDtos.add(paperDto);
        }

        return paperDtos;
    }
}

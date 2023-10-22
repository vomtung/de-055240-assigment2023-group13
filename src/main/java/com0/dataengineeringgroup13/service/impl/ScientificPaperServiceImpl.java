package com0.dataengineeringgroup13.service.impl;

import com0.dataengineeringgroup13.service.ScientificPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ScientificPaperServiceImpl implements ScientificPaperService {

    @Autowired
    private Connection dataConnection;

    public void truncateScientificPaper() throws SQLException {
        Statement stmt = dataConnection.createStatement();

        stmt.executeQuery("TRUNCATE CLASS PAPER UNSAFE;");
    }
}

package com0.dataengineeringgroup13.service.impl;

import com0.dataengineeringgroup13.dto.PaperDetailDto;
import com0.dataengineeringgroup13.dto.UserDetailDto;
import com0.dataengineeringgroup13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * User service
 *
 * @author Tung Vo
 * @since 08.Nov.2023
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Connection dataConnection;

    @Override
    public void updateUser(UserDetailDto userDetailDto) throws SQLException {

        System.out.println("===updateUser(UserDetailDto userDetailDto)="+userDetailDto.getUserId());

        Statement stmt = dataConnection.createStatement();
        ResultSet rs = stmt.executeQuery("UPDATE USER SET FIRST_NAME =  '"+ userDetailDto.getFirstName() +"'," +
                "LAST_NAME =  '"+ userDetailDto.getLastName() +"'," +
                "EMAIL =  '"+ userDetailDto.getEmail() + "'" +
                "  UPSERT WHERE USR_ID = "+ userDetailDto.getUserId());
    }
}

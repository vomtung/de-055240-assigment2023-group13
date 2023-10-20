package com0.dataengineeringgroup13.controller;

import com.github.javafaker.Faker;
import com0.dataengineeringgroup13.common.AppContanst;
import com0.dataengineeringgroup13.dto.ArticleDto;
import com0.dataengineeringgroup13.dto.ScholarDto;
import com0.dataengineeringgroup13.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@Controller
public class UserController {

    @Value("${orientdb.connection.url}")
    private String connectionUrl;

    @Value("${orientdb.username}")
    private String username;

    @Value("${orientdb.password}")
    private String password;

    @GetMapping("/user")
    public String index(@RequestParam(required = false) Integer pageNumber, Model model) throws Exception {

        Properties info = new Properties();
        info.put("user", username);
        info.put("password", password);

        Connection conn = DriverManager.getConnection(connectionUrl, info);

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = 0;
        }

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM  USER  SKIP "+ AppContanst.NUMBER_RECORD_PER_PAGE * pageNumber +
                " LIMIT " + AppContanst.NUMBER_RECORD_PER_PAGE);

        List<UserDto> userList = new ArrayList<>();
        while (rs.next()) {
            UserDto dto = new UserDto();
            dto.setUserId(rs.getInt("USR_ID"));
            dto.setFirstName(rs.getString("FIRST_NAME"));
            dto.setLastName(rs.getString("LAST_NAME"));
            userList.add(dto);
        }

        model.addAttribute("userList", userList);
        model.addAttribute("pageNumber", pageNumber);

        return "user";
    }

    @GetMapping("/user/generate-user")
    public String genegrateuser(Model model) throws Exception {

        Properties info = new Properties();
        info.put("user", username);
        info.put("password", password);

        Connection conn = DriverManager.getConnection(connectionUrl, info);
        Statement stmt = conn.createStatement();

        for (int i = 10; i < 20; i++) {

            Faker faker1 = new Faker();
            String firstName = faker1.name().firstName();
            String lastName = faker1.name().lastName();
            String username = faker1.name().username();
            String email = faker1.name().username() + "@mail.kom";

            firstName = firstName.replaceAll("[^A-Za-z0-9]","");
            lastName = lastName.replaceAll("[^A-Za-z0-9]","");
            username = username.replaceAll("[^A-Za-z0-9]","");
            email = email.replaceAll("[^A-Za-z0-9]","");

            System.out.println("generateuser-firstName:" + firstName);
            System.out.println("generateuser-lastName:" + lastName);


            ResultSet rs = stmt.executeQuery("INSERT INTO    USER(USR_ID, USR_NAME, FIRST_NAME, EMAIL, LAST_NAME) " +
                    "            VALUES("+ i+",'"+username+"', '"+firstName+"','"+email+"' ,'"+lastName+"')"
            );
            /*INSERT INTO    Employee(name, surname, gender)
            VALUES('Jay', 'Miner', 'M')*/
        }

        return "generate-result";
    }

    @GetMapping("/user/truncate")
    public String truncateAcademicRank(Model model) throws Exception {

        Properties info = new Properties();
        info.put("user", username);
        info.put("password", password);

        Connection conn = DriverManager.getConnection(connectionUrl, info);
        Statement stmt = conn.createStatement();

        stmt.executeQuery("TRUNCATE CLASS USER UNSAFE;");

        return "generate-result";
    }
}

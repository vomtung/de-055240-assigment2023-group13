package com0.dataengineeringgroup13.controller;

import com.github.javafaker.Faker;
import com0.dataengineeringgroup13.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.*;
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
    public String index(Model model) {

        ArticleDto article1 = new ArticleDto(1L,"title 123","content23123");
        ArticleDto article2 = new ArticleDto(1L,"title 222","content222222222");
        model.addAttribute("artiles", List.of(article1, article2));
        return "home";
    }

    @GetMapping("/user/generateuser")
    public String genegrateuser(Model model) throws Exception {

        Properties info = new Properties();
        info.put("user", username);
        info.put("password", password);

        Connection conn = DriverManager.getConnection(connectionUrl, info);
        Statement stmt = conn.createStatement();

        for (int i = 0; i< 50; i++) {

            Faker faker1 = new Faker();
            String firstName = faker1.name().firstName();
            String lastName = faker1.name().lastName();
            String username = faker1.name().username();
            String email = faker1.name().name() + "@mail.kom";


            System.out.println("generateuser-firstName:" + firstName);
            System.out.println("generateuser-lastName:" + lastName);


            ResultSet rs = stmt.executeQuery("INSERT INTO    USER(USR_ID, USR_NAME, FIRST_NAME, EMAIL, LAST_NAME) " +
                    "            VALUES("+ i +",'"+username+"', '"+firstName+"','"+email+"' ,'"+lastName+"')"
            );
            /*INSERT INTO    Employee(name, surname, gender)
            VALUES('Jay', 'Miner', 'M')*/
        }


        ArticleDto article1 = new ArticleDto(1L,"title 123","content23123");
        ArticleDto article2 = new ArticleDto(1L,"title 222","content222222222");
        model.addAttribute("artiles", List.of(article1, article2));
        return "generate-result";
    }
}

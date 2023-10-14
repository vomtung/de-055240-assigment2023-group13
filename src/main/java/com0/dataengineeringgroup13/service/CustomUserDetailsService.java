package com0.dataengineeringgroup13.service;

import com0.dataengineeringgroup13.common.AppContanst;
import com0.dataengineeringgroup13.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Value("${orientdb.connection.url}")
    private String connectionUrl;

    @Value("${orientdb.username}")
    private String dbUsername;

    @Value("${orientdb.password}")
    private String dbPassword;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //User user = userRepository.findByEmail(usernameOrEmail);
        Properties info = new Properties();
        info.put("user", dbUsername);
        info.put("password", dbPassword);

        Statement stmt = null;

        try {

            Connection conn = DriverManager.getConnection(connectionUrl, info);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM  USER  WHERE USR_NAME = '" + username + "'");

            User user = null;
            while (rs.next()) {

                user = new User(rs.getString("USR_NAME"), "$2a$12$cMOcsjy0bKlBjxu/26WqmecmXKckqsERG6Pb/0b0vRo1p8wOFTPi.", List.of());
            }

            if (user != null) {
                System.out.println("----" + user.getUsername());
                return new org.springframework.security.core.userdetails.User("admin@mail.com",
                        "$2a$12$cMOcsjy0bKlBjxu/26WqmecmXKckqsERG6Pb/0b0vRo1p8wOFTPi.",
                        List.of());


            } else {
                throw new UsernameNotFoundException("Invalid email or password");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

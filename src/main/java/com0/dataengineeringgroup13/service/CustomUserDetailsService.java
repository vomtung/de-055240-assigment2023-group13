package com0.dataengineeringgroup13.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User user = userRepository.findByEmail(usernameOrEmail);

        User user = new User("admin", "$2a$12$cMOcsjy0bKlBjxu/26WqmecmXKckqsERG6Pb/0b0vRo1p8wOFTPi.", List.of());

        if (user != null) {
            System.out.println("----" + user.getUsername());
            return new org.springframework.security.core.userdetails.User("admin@mail.com",
                    "$2a$12$cMOcsjy0bKlBjxu/26WqmecmXKckqsERG6Pb/0b0vRo1p8wOFTPi.",
                    List.of());


        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}

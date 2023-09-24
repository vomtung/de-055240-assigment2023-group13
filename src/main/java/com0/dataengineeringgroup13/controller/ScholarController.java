package com0.dataengineeringgroup13.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ScholarController {

    @GetMapping("/scholar")
    public String showForm() {
        return "scholar";
    }
}

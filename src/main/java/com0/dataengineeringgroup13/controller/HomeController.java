package com0.dataengineeringgroup13.controller;

import com0.dataengineeringgroup13.dto.ArticleDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showForm(Model model) {

        ArticleDto article1 = new ArticleDto(1L,"title 123","content23123");
        ArticleDto article2 = new ArticleDto(1L,"title 222","content222222222");
        model.addAttribute("artiles", List.of(article1, article2));
        return "home";
    }
}

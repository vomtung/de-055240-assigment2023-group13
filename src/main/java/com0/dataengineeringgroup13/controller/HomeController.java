package com0.dataengineeringgroup13.controller;

import com0.dataengineeringgroup13.dto.ArticleDto;
import com0.dataengineeringgroup13.service.ScientificPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

@Controller
public class HomeController {

    @Autowired
    private ScientificPaperService scientificPaperService;

    @GetMapping("/")
    public String index(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ArticleDto article1 = new ArticleDto(1L,"title 123","content23123");
        ArticleDto article2 = new ArticleDto(1L,"title 222","content222222222");

        String currentUser = null;
        if (auth != null && !auth.getName().equals("anonymousUser")) {
            currentUser = auth.getName();
        }

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("artiles", List.of(article1, article2));

        return "home";
    }

    @GetMapping("/page/truncate")
    public String truncateAcademicRank(Model model) throws Exception {

        scientificPaperService.truncateScientificPaper();

        return "generate-result";
    }
}

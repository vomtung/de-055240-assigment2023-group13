package com0.dataengineeringgroup13.controller;

import com0.dataengineeringgroup13.dto.PaperDto;
import com0.dataengineeringgroup13.service.ScientificPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ScientificPaperService scientificPaperService;

    @GetMapping("/")
    public String index(@RequestParam(required = false) Integer pageNumber, Model model) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        String currentUser = null;
        if (auth != null && !auth.getName().equals("anonymousUser")) {
            currentUser = auth.getName();
        }


        model.addAttribute("currentUser", currentUser);
        model.addAttribute("pageNumber", pageNumber);
        if (pageNumber == null || pageNumber < 0) {
            model.addAttribute("pageNumber", 0);
            model.addAttribute("artiles", scientificPaperService.findAll(0));
        }

        return "home";
    }

    @GetMapping("/page/truncate")
    public String truncateAcademicRank(Model model) throws Exception {

        scientificPaperService.truncateScientificPaper();

        return "generate-result";
    }
}

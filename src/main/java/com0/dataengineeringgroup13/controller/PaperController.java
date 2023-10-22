package com0.dataengineeringgroup13.controller;

import com0.dataengineeringgroup13.service.ScientificPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaperController {

    @Autowired
    private ScientificPaperService scientificPaperService;

    @GetMapping("/paper/truncate")
    public String truncateAcademicRank(Model model) throws Exception {

        scientificPaperService.truncateScientificPaper();

        return "generate-result";
    }
}

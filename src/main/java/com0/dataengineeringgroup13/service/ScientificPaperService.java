package com0.dataengineeringgroup13.service;

import com0.dataengineeringgroup13.dto.PaperDto;

import java.sql.SQLException;
import java.util.List;

public interface ScientificPaperService {

    void truncateScientificPaper() throws SQLException;

    List<PaperDto> findAll(Integer pageNumber) throws SQLException;
}

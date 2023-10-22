package com0.dataengineeringgroup13.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public interface ImportExcelAsyncService {

    CompletableFuture<Boolean> conductAsynImportExcelFile(XSSFWorkbook workbook, Integer rowFactor) throws SQLException;
}

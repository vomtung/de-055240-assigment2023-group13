package com0.dataengineeringgroup13.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.concurrent.CompletableFuture;

public interface ImportExcelAsyncService {

    void importExcelFileAsync(XSSFWorkbook workbook);

    CompletableFuture<Boolean> conductAsynImportExcelFile(XSSFWorkbook workbook, Integer rowFactor);
}

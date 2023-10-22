package com0.dataengineeringgroup13.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.concurrent.CompletableFuture;

public interface ImportExcelService {

    void importExcelFileAsync(XSSFWorkbook workbook);

}

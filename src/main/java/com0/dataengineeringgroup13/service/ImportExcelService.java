package com0.dataengineeringgroup13.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ImportExcelService {

    void importExcelFileAsync(XSSFWorkbook workbook) throws Exception;

}

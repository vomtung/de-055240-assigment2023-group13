package com0.dataengineeringgroup13.dto;

public class ScientificPaper {

    private String subject;
    private String content;
    private String author;

    private String excelRowNum;

    public ScientificPaper() {
    }

    public ScientificPaper(String subject, String content, String author, String excelRowNum) {
        this.subject = subject;
        this.content = content;
        this.author = author;
        this.excelRowNum = excelRowNum;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getExcelRowNum() {
        return excelRowNum;
    }

    public void setExcelRowNum(String excelRowNum) {
        this.excelRowNum = excelRowNum;
    }
}

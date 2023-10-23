package com0.dataengineeringgroup13.dto;

import java.io.Serializable;

/**
 * Paper detail
 *
 * @author Tung Vo
 * @since 23/Otc/2023
 */
public class PaperDetailDto implements Serializable {
    private Long paperId;
    private String subject;
    private String content;
    private String author;
    private String userIdentifier;

    public PaperDetailDto() {
    }

    public PaperDetailDto(Long paperId, String subject, String content, String userIdentifier) {
        this.paperId = paperId;
        this.subject = subject;
        this.content = content;
        this.userIdentifier = userIdentifier;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
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

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }
}

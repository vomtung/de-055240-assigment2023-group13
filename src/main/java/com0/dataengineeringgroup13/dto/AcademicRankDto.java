package com0.dataengineeringgroup13.dto;

public class AcademicRankDto {

    private Long acaId;
    private String rankName;
    private String abbreviation;

    public Long getAcaId() {
        return acaId;
    }

    public void setAcaId(Long acaId) {
        this.acaId = acaId;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}

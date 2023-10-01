package com0.dataengineeringgroup13.dto;

import java.io.Serializable;

/**
 * Academic rank dto
 *
 * @author Tung Vo
 * @since 01/October/2023
 */
public class AcademicRankDto implements Serializable {

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

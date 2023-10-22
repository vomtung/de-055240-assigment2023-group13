package com0.dataengineeringgroup13.contants;

/**
 * Column index
 *
 * @author Tung Vo
 * @since 22/Otc/2023
 */
public enum PaperColumnIndex {

    SUBJECT_PAPER(0),
    CONTENT_PAPER(1),
    AUTHOR_PAPER(2);
    private Integer columnIndex;

    private PaperColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }
}

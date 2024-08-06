package com.wanted.recruitment.model;

public enum SearchType {
    COMPANY_NAME("companyName"),
    POSITION("position"),
    SKILLS("skills"),
    ;

    private final String columnName;

    SearchType(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}

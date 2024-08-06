package com.wanted.recruitment.model;

public enum ApplicationStatus {
    APPLIED("지원 완료"),
    ACCEPTED("합격"),
    REJECTED("불합격"),
    ;

    private final String description;

    ApplicationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

package com.wanted.recruitment.controller.request;

import lombok.Getter;

@Getter
public class JobCreateRequest {
    private Long companyId;
    private String position;
    private Integer reward;
    private String description;
    private String skills;
}

package com.wanted.recruitment.controller.request;

import lombok.Getter;

@Getter
public class JobUpdateRequest {
    private Long jobId;
    private Long companyId;
    private String position;
    private Integer reward;
    private String description;
    private String skills;
}

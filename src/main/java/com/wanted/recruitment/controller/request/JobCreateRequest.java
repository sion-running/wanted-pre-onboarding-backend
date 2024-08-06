package com.wanted.recruitment.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobCreateRequest {
    private Long companyId;
    private String position;
    private Integer reward;
    private String description;
    private String skills;
}

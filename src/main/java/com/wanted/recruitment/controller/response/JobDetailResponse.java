package com.wanted.recruitment.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wanted.recruitment.model.entity.Company;
import com.wanted.recruitment.model.entity.Job;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public class JobDetailResponse {
    @JsonProperty("채용공고_id")
    private Long jobId;
    @JsonProperty("회사명")
    private String companyName;
    @JsonProperty("국가")
    private String country;
    @JsonProperty("지역")
    private String city;
    @JsonProperty("채용 포지션")
    private String position;
    @JsonProperty("채용 보상금")
    private Integer reward;
    @JsonProperty("사용 기술")
    private String skills;

    @JsonProperty("채용 내용")
    private String description;
    @JsonProperty("회사가 올린 다른 채용공고")
    private List<Long> jobIds;

    public static JobDetailResponse fromJob(Job job) {
        Company company = job.getCompany();

        return JobDetailResponse.builder()
                .jobId(job.getId())
                .companyName(company.getName())
                .country(company.getCountry())
                .city(company.getCity())
                .position(job.getPosition())
                .reward(job.getReward())
                .skills(job.getSkills())
                .description(job.getDescription())
                .jobIds(company.getJobs().stream().map(Job::getId).collect(Collectors.toList()))
                .build();
    }
}

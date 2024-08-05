package com.wanted.recruitment.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wanted.recruitment.model.entity.Company;
import com.wanted.recruitment.model.entity.Job;
import com.wanted.recruitment.repository.JobRepository;
import lombok.Builder;

import java.util.List;

@Builder
public class JobResponse {
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

    public static JobResponse fromJob(Job job) {
        Company company = job.getCompany();

        return JobResponse.builder()
                .jobId(job.getId())
                .companyName(company.getName())
                .country(company.getCountry())
                .city(company.getCity())
                .position(job.getPosition())
                .reward(job.getReward())
                .skills(job.getSkills())
                .build();
    }
}

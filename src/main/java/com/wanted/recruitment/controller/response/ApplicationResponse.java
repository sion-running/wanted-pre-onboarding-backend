package com.wanted.recruitment.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wanted.recruitment.model.ApplicationStatus;
import com.wanted.recruitment.model.entity.Application;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ApplicationResponse {
    @JsonProperty("채용공고_id")
    private Long jobId;
    @JsonProperty("사용자_id")
    private Long userId;
    @JsonProperty("지원 일자")
    private LocalDateTime appliedAt;
    @JsonProperty("지원 상태")
    private String status;

    public static ApplicationResponse fromEntity(Application entity) {
        return ApplicationResponse.builder()
                .jobId(entity.getJobId())
                .userId(entity.getUserId())
                .appliedAt(entity.getAppliedAt())
                .status(entity.getStatus().getDescription())
                .build();
    }
}

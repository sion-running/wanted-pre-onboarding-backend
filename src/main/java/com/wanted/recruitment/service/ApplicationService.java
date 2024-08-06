package com.wanted.recruitment.service;

import com.wanted.recruitment.controller.response.ApplicationResponse;
import com.wanted.recruitment.exception.ErrorCode;
import com.wanted.recruitment.exception.RecruitmentApplicationException;
import com.wanted.recruitment.model.ApplicationStatus;
import com.wanted.recruitment.model.entity.Application;
import com.wanted.recruitment.repository.ApplicationRepository;
import com.wanted.recruitment.repository.JobRepository;
import com.wanted.recruitment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public ApplicationResponse apply(Long jobId, Long userId) {
        // 채용공고 확인
        jobRepository.findById(jobId).orElseThrow(() -> {
            throw new RecruitmentApplicationException(ErrorCode.JOB_NOT_FOUND, jobId);
        });

        // 유저 확인
        userRepository.findById(userId).orElseThrow(() -> {
            throw new RecruitmentApplicationException(ErrorCode.USER_NOT_FOUND, userId);
        });

        // 중복 지원 확인
        applicationRepository.findByJobIdAndUserId(jobId, userId).ifPresent(a -> {
            throw new RecruitmentApplicationException(ErrorCode.DUPLICATE_APPLICATION, userId);
        });

        // save
        Application application = Application.builder()
                .jobId(jobId)
                .userId(userId)
                .status(ApplicationStatus.APPLIED)
                .build();

        Application saved = applicationRepository.save(application);
        return ApplicationResponse.fromEntity(saved);
    }
}

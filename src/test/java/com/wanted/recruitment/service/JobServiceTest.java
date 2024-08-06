package com.wanted.recruitment.service;

import com.wanted.recruitment.controller.request.JobCreateRequest;
import com.wanted.recruitment.controller.request.JobUpdateRequest;
import com.wanted.recruitment.exception.ErrorCode;
import com.wanted.recruitment.exception.RecruitmentApplicationException;
import com.wanted.recruitment.model.entity.Company;
import com.wanted.recruitment.model.entity.Job;
import com.wanted.recruitment.repository.CompanyRepository;
import com.wanted.recruitment.repository.JobRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {
    @InjectMocks
    JobService jobService;

    @Mock
    JobRepository jobRepository;

    @Mock
    CompanyRepository companyRepository;

    @Test
    void 채용공고_생성시_존재하지_않는_회사일_경우_예외를_발생시킨다() {
        // given
        JobCreateRequest request = JobCreateRequest.builder()
                .companyId(100L)
                .build();

        // when
        when(companyRepository.findById(request.getCompanyId())).thenReturn(Optional.empty());

        // then
        RecruitmentApplicationException exception = assertThrows(RecruitmentApplicationException.class, () ->
                jobService.create(request));

        Assertions.assertEquals(ErrorCode.COMPANY_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    void 채용공고_수정시_유효하지_않은_채용공고면_예외를_발생시킨다() {
        // given
        JobUpdateRequest request = JobUpdateRequest.builder()
                .jobId(100L)
                .build();

        // when
        when(jobRepository.findById(request.getJobId())).thenReturn(Optional.empty());

        // then
        RecruitmentApplicationException exception = assertThrows(RecruitmentApplicationException.class, () ->
                jobService.update(request));

        Assertions.assertEquals(ErrorCode.JOB_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    void 채용공고_수정시_회사_아이디는_수정불가() {
        // given
        JobUpdateRequest request = JobUpdateRequest.builder()
                .jobId(1L)
                .companyId(2L)
                .build();

        Company company = Company.builder().id(1L).build();
        Job existingJob = Job.builder()
                .id(1L)
                .company(company)
                .build();

        // when
        when(jobRepository.findById(request.getJobId())).thenReturn(Optional.ofNullable(existingJob));

        // then
        RecruitmentApplicationException exception = assertThrows(RecruitmentApplicationException.class, () ->
                jobService.update(request));

        Assertions.assertEquals(ErrorCode.INVALID_REQUEST, exception.getErrorCode());
    }

    @Test
    void 채용공고_삭제시_채용공고가_존재하지_않으면_예외를_발생시킨다() {
        // given
        Long requestJobId = 1L;

        // when
        when(jobRepository.findById(requestJobId)).thenReturn(Optional.empty());

        // then
        RecruitmentApplicationException exception = assertThrows(RecruitmentApplicationException.class, () ->
                jobService.delete(requestJobId));

        Assertions.assertEquals(ErrorCode.JOB_NOT_FOUND, exception.getErrorCode());
    }

}
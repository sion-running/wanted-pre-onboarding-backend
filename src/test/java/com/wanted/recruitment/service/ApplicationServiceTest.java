package com.wanted.recruitment.service;

import com.wanted.recruitment.exception.ErrorCode;
import com.wanted.recruitment.exception.RecruitmentApplicationException;
import com.wanted.recruitment.model.entity.Application;
import com.wanted.recruitment.model.entity.Job;
import com.wanted.recruitment.model.entity.User;
import com.wanted.recruitment.repository.ApplicationRepository;
import com.wanted.recruitment.repository.JobRepository;
import com.wanted.recruitment.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {
    @InjectMocks
    ApplicationService applicationService;

    @Mock
    ApplicationRepository applicationRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    JobRepository jobRepository;

    @Test
    void 존재하지_않는_채용공고에_지원시_예외를_발생시킨다() {
        // given
        Long fakeJobId = 100L;
        Long userId = 1L;

        // when
        when(jobRepository.findById(fakeJobId)).thenReturn(Optional.empty());

        // then
        RecruitmentApplicationException exception = assertThrows(RecruitmentApplicationException.class, () ->
                applicationService.apply(fakeJobId, userId));

        Assertions.assertEquals(ErrorCode.JOB_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    void 존재하지_않는_유저가_지원시_예외를_발생시킨다() {
        // given
        Long jobId = 100L;
        Long fakeUserId = 1L;

        // when
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(mock(Job.class)));
        when(userRepository.findById(fakeUserId)).thenReturn(Optional.empty());

        // then
        RecruitmentApplicationException exception = assertThrows(RecruitmentApplicationException.class, () ->
                applicationService.apply(jobId, fakeUserId));

        Assertions.assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    void 유저가_같은_채용공고에_두_번_지원하면_예외를_발생시킨다() {
        // given
        Long jobId = 100L;
        Long userId = 1L;

        // when
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(mock(Job.class)));
        when(userRepository.findById(userId)).thenReturn(Optional.of(mock(User.class)));
        when(applicationRepository.findByJobIdAndUserId(jobId, userId)).thenReturn(Optional.of(mock(Application.class)));

        // then
        RecruitmentApplicationException exception = assertThrows(RecruitmentApplicationException.class, () ->
                applicationService.apply(jobId, userId));

        Assertions.assertEquals(ErrorCode.DUPLICATE_APPLICATION, exception.getErrorCode());
    }
}
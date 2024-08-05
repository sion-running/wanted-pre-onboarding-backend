package com.wanted.recruitment.service;

import com.wanted.recruitment.controller.response.JobDetailResponse;
import com.wanted.recruitment.controller.response.JobResponse;
import com.wanted.recruitment.model.entity.Job;
import com.wanted.recruitment.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;

    public List<JobResponse> getJobList() {
        return jobRepository.findAllWithCompany().stream().map(JobResponse::fromJob).collect(Collectors.toList());
    }

    public JobDetailResponse getJobDetail(Long jobId) {
        Job job = jobRepository.findByIdWithCompany(jobId);
        return JobDetailResponse.fromJob(job);
    }
}

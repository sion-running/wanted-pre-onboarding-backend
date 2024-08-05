package com.wanted.recruitment.controller;

import com.wanted.recruitment.controller.response.JobDetailResponse;
import com.wanted.recruitment.controller.response.JobResponse;
import com.wanted.recruitment.model.entity.Job;
import com.wanted.recruitment.repository.JobRepository;
import com.wanted.recruitment.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job")
public class JobController {
    private final JobRepository jobRepository;
    private final JobService jobService;

    @GetMapping
    public List<JobResponse> getJobList() {
        return jobService.getJobList();
    }

    @GetMapping("/{jobId}")
    public JobDetailResponse getJobDetail(@PathVariable("jobId") Long jobId) {
        return jobService.getJobDetail(jobId);
    }
}

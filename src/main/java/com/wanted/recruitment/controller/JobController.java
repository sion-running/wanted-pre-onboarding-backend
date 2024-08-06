package com.wanted.recruitment.controller;

import com.wanted.recruitment.controller.request.JobCreateRequest;
import com.wanted.recruitment.controller.request.JobUpdateRequest;
import com.wanted.recruitment.controller.response.BaseResponse;
import com.wanted.recruitment.controller.response.JobDetailResponse;
import com.wanted.recruitment.controller.response.JobResponse;
import com.wanted.recruitment.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job")
public class JobController {
    private final JobService jobService;

    @PostMapping
    public BaseResponse<JobResponse> create(@RequestBody JobCreateRequest request) {
        JobResponse jobResponse = jobService.create(request);
        return BaseResponse.success(jobResponse);
    }

    @PutMapping
    public BaseResponse<Void> update(@RequestBody JobUpdateRequest request) {
        jobService.update(request);
        return BaseResponse.success();
    }

    @DeleteMapping("/{jobId}")
    public BaseResponse<Void> update(@PathVariable("jobId") Long jobId) {
        jobService.delete(jobId);
        return BaseResponse.success();
    }

    @GetMapping
    public BaseResponse<List<JobResponse>> getJobList() {
        return BaseResponse.success(jobService.getJobList());
    }

    @GetMapping("/{jobId}")
    public BaseResponse<JobDetailResponse> getJobDetail(@PathVariable("jobId") Long jobId) {
        return BaseResponse.success(jobService.getJobDetail(jobId));
    }
}

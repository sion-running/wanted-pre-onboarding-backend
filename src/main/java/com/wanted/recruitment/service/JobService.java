package com.wanted.recruitment.service;

import com.wanted.recruitment.controller.request.JobCreateRequest;
import com.wanted.recruitment.controller.request.JobUpdateRequest;
import com.wanted.recruitment.controller.response.JobDetailResponse;
import com.wanted.recruitment.controller.response.JobResponse;
import com.wanted.recruitment.exception.ErrorCode;
import com.wanted.recruitment.exception.RecruitmentApplicationException;
import com.wanted.recruitment.model.entity.Company;
import com.wanted.recruitment.model.entity.Job;
import com.wanted.recruitment.repository.CompanyRepository;
import com.wanted.recruitment.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobResponse create(JobCreateRequest request) {
        Long companyId = request.getCompanyId();
        Company company = companyRepository.findById(companyId).orElseThrow(() -> {
            throw new RecruitmentApplicationException(ErrorCode.COMPANY_NOT_FOUND, companyId);
        });

        Job job = Job.fromRequest(request, company);
        return JobResponse.fromJob(jobRepository.save(job));
    }

    public void update(JobUpdateRequest request) {
        Job existingJob = jobRepository.findById(request.getJobId()).orElseThrow(() -> {
            throw new RecruitmentApplicationException(ErrorCode.JOB_NOT_FOUND, request.getJobId());
        });

        if (request.getCompanyId() != existingJob.getCompany().getId()) {
            throw new RecruitmentApplicationException(ErrorCode.INVALID_REQUEST, request.getJobId());
        }

        existingJob.setPosition(request.getPosition());
        existingJob.setReward(request.getReward());
        existingJob.setDescription(request.getDescription());
        existingJob.setSkills(request.getSkills());

        jobRepository.save(existingJob);
    }



    public List<JobResponse> getJobList() {
        return jobRepository.findAllWithCompany().stream().map(JobResponse::fromJob).collect(Collectors.toList());
    }

    public JobDetailResponse getJobDetail(Long jobId) {
        Job job = jobRepository.findByIdWithCompany(jobId);
        return JobDetailResponse.fromJob(job);
    }
}

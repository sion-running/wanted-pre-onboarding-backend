package com.wanted.recruitment.service;

import com.wanted.recruitment.controller.request.JobCreateRequest;
import com.wanted.recruitment.controller.request.JobSearchRequest;
import com.wanted.recruitment.controller.request.JobUpdateRequest;
import com.wanted.recruitment.controller.response.JobDetailResponse;
import com.wanted.recruitment.controller.response.JobResponse;
import com.wanted.recruitment.exception.ErrorCode;
import com.wanted.recruitment.exception.RecruitmentApplicationException;
import com.wanted.recruitment.model.SearchType;
import com.wanted.recruitment.model.entity.Company;
import com.wanted.recruitment.model.entity.Job;
import com.wanted.recruitment.repository.CompanyRepository;
import com.wanted.recruitment.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public JobResponse create(JobCreateRequest request) {
        Long companyId = request.getCompanyId();
        Company company = companyRepository.findById(companyId).orElseThrow(() -> {
            throw new RecruitmentApplicationException(ErrorCode.COMPANY_NOT_FOUND, companyId);
        });

        Job job = Job.fromRequest(request, company);
        return JobResponse.fromJob(jobRepository.save(job));
    }

    @Transactional
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

    @Transactional
    public void delete(Long jobId) {
        Job existingJob = jobRepository.findById(jobId).orElseThrow(() -> {
            throw new RecruitmentApplicationException(ErrorCode.JOB_NOT_FOUND, jobId);
        });

        jobRepository.delete(existingJob);
    }

    @Transactional(readOnly = true)
    public List<JobResponse> getJobList() {
        return jobRepository.findAllWithCompany().stream().map(JobResponse::fromJob).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public JobDetailResponse getJobDetail(Long jobId) {
        Job job = jobRepository.findByIdWithCompany(jobId);
        return JobDetailResponse.fromJob(job);
    }

    @Transactional(readOnly = true)
    public List<JobResponse> search(JobSearchRequest searchRequest) {
        SearchType searchType = searchRequest.getSearchType();
        String keyword = searchRequest.getKeyword();
        List<JobResponse> list = new ArrayList<>();

        if (isNullOrEmpty(keyword)) {
            return getJobList();
        }

        switch (searchType) {
            case COMPANY_NAME:
                return jobRepository.findByCompanyNameLike(keyword).stream().map(JobResponse::fromJob).collect(Collectors.toList());
            case POSITION:
                return jobRepository.findByPositionLike(keyword).stream().map(JobResponse::fromJob).collect(Collectors.toList());
            case SKILLS:
                return jobRepository.findBySkillsLike(keyword).stream().map(JobResponse::fromJob).collect(Collectors.toList());
            default:
                throw new RecruitmentApplicationException(ErrorCode.INVALID_REQUEST);
        }
    }

    private boolean isNullOrEmpty(String str) {
        return Optional.ofNullable(str)
                .filter(s -> !s.isEmpty())
                .isEmpty();
    }

}

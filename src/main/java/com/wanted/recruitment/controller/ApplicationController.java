package com.wanted.recruitment.controller;

import com.wanted.recruitment.controller.response.ApplicationResponse;
import com.wanted.recruitment.controller.response.BaseResponse;
import com.wanted.recruitment.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;

    // 지원하기
    @PostMapping
    public BaseResponse<ApplicationResponse> apply(@RequestParam("jobId") Long jobId, @RequestParam("userId") Long userId) {
        ApplicationResponse response = applicationService.apply(jobId, userId);
        return BaseResponse.success(response);
    }


}

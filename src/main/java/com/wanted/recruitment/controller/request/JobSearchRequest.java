package com.wanted.recruitment.controller.request;

import com.wanted.recruitment.model.SearchType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobSearchRequest {
    private SearchType searchType;
    private String keyword;
}

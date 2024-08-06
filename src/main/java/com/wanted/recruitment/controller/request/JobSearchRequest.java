package com.wanted.recruitment.controller.request;

import com.wanted.recruitment.model.SearchType;
import lombok.Getter;

@Getter
public class JobSearchRequest {
    private SearchType searchType;
    private String keyword;

}

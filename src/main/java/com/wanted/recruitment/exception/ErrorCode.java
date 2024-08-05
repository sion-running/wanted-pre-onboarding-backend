package com.wanted.recruitment.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected system error occurred."),
    Job_NOT_FOUND(HttpStatus.NOT_FOUND, "The job with the specified ID does not exist."),
    ;

    private final HttpStatus status;
    private final String desc;
}

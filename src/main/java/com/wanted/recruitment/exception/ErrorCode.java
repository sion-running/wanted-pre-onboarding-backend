package com.wanted.recruitment.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected system error occurred."),
    JOB_NOT_FOUND(HttpStatus.NOT_FOUND, "The job with the specified ID does not exist."),
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "The company with the specified ID does not exist."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request."),
    NO_SEARCH_KEYWORD(HttpStatus.BAD_REQUEST, "No search keyword provided in the client request."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "Invalid Request. User not found"),
    DUPLICATE_APPLICATION(HttpStatus.BAD_REQUEST, "User has already applied for this job.");

    private final HttpStatus status;
    private final String desc;
}

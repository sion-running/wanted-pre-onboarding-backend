package com.wanted.recruitment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class RecruitmentApplicationException extends RuntimeException {
    private ErrorCode errorCode;
    private Long numberParam;


    public RecruitmentApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public RecruitmentApplicationException(ErrorCode errorCode, Long param) {
        this.errorCode = errorCode;
        this.numberParam = param;
    }

    @Override
    public String getMessage() {
        if (numberParam != null) {
            return String.format("%s --- caused by %s", errorCode.getDesc(), numberParam);
        }

        return errorCode.getDesc();
    }
}

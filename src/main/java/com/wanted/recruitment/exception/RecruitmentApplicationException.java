package com.wanted.recruitment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecruitmentApplicationException extends RuntimeException {
    private ErrorCode errorCode;
    private String desc;


    public RecruitmentApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.desc = null;
    }

    @Override
    public String getMessage() {
        if (desc == null) {
            return errorCode.getDesc();
        } else {
            return String.format("%s. %s", errorCode.getDesc(), desc);
        }
    }
}

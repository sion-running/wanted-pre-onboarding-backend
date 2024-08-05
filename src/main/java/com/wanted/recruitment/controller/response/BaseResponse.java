package com.wanted.recruitment.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private String resultCode;
    private T result;

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<T>("SUCCESS", null);
    }

    public static <T> BaseResponse<T> success(T result) {
        return new BaseResponse<T>("SUCCESS", result);
    }

    public static BaseResponse<Void> error(String resultCode) {
        return new BaseResponse<Void>(resultCode, null);
    }

    public String toStream() {
        if (result == null) {
            return "{" +
                    "\"resultCode\":" + "\"" + resultCode + "\"," +
                    "\"result\":" + null +
                    "}";
        }
        return "{" +
                "\"resultCode\":" + "\"" + resultCode + "\"," +
                "\"result\":" + "\"" + result + "\"," +
                "}";
    }
}

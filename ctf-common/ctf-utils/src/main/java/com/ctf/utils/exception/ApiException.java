package com.ctf.utils.exception;


import com.lenovo.result.ResultCode;
import lombok.Getter;

/**
 * 自定义API异常
 * Created by macro on 2020/2/27.
 */
@Getter
public class ApiException extends RuntimeException {

    private String code;

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ApiException(ResultCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public ApiException(String message) {
        super(message);
    }


}

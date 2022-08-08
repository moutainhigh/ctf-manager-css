package com.ctf.common.web.exception;

import com.ctf.common.result.IResultCode;
import lombok.Getter;

/**
 * 自定义业务异常
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Getter
public class BusinessException extends RuntimeException {

    public IResultCode resultCode;

    public BusinessException(IResultCode errorCode) {
        super(errorCode.getMsg());
        this.resultCode = errorCode;
    }

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, Throwable cause){
        super(message, cause);
    }

    public BusinessException(Throwable cause){
        super(cause);
    }


}

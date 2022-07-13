package com.ctf.utils.exception;


import com.lenovo.result.ResultCode;

/**
 * 断言处理类，用于抛出各种API异常
 * Created by macro on 2020/2/27.
 */
public class Asserts {

    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(ResultCode errorCode) {
        throw new ApiException(errorCode);
    }

    public static void fail(ResultCode errorCode, String message) {
        throw new ApiException(errorCode.getCode(),message);
    }

    public static void fail(String code,String message) {
        throw new ApiException(code,message);
    }

}

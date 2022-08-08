package com.ctf.generator.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误编码
 *
 * @author H.m
*/
@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("500", "服务器异常，请稍后再试");

    private final String code;
    private final String msg;
}

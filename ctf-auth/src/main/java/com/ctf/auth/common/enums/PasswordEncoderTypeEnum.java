package com.ctf.auth.common.enums;

import lombok.Getter;

/**
 * 密码枚举
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public enum PasswordEncoderTypeEnum {

    BCRYPT("{bcrypt}","BCRYPT加密"),
    NOOP("{noop}","无加密明文");

    @Getter
    private String prefix;

    PasswordEncoderTypeEnum(String prefix, String desc){
        this.prefix=prefix;
    }

}

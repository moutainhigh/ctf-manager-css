package com.ctf.auth.security.extension.wechat;

import lombok.Data;

/**
 * 微信用户信息
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Data
public class WechatUserInfo {
    private String avatarUrl;

    private String city;

    private String country;

    private Integer gender;

    private String language;

    private String nickName;

    private String province;

}

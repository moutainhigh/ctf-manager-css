package com.ctf.ums.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * 会员传输层对象
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Data
public class MemberDTO {

    private Integer gender;

    private String nickName;

    private String mobile;

    private LocalDate birthday;

    private String avatarUrl;

    private String openid;

    private String sessionKey;

    private String city;

    private String country;

    private String language;

    private String province;

}

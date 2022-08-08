package com.ctf.ums.dto;

import lombok.Data;


/**
 * 会员传输层对象
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Data
public class MemberInfoDTO {

    private String nickName;

    private String avatarUrl;

    private Long balance;

}

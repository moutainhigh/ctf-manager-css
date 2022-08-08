package com.ctf.ums.dto;

import lombok.Data;

/**
 * 会员地址传输层对象
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Data
public class MemberAddressDTO {

    private Long id;

    private Long memberId;

    private String consigneeName;

    private String consigneeMobile;

    private String province;

    private String city;

    private String area;

    private String detailAddress;

    private Integer defaulted;

}




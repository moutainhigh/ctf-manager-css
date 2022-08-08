package com.ctf.lab.spring.aop.transactional;

import com.ctf.common.base.BaseEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 说明描述
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Data
public class UmsMember extends BaseEntity {

    private Long id;

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

    private Integer status;

    private Long balance;

    private Integer deleted;

    private List<UmsAddress> addressList;

    private Integer point;

}

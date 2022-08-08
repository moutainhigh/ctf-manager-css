package com.ctf.ums.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 会员认证传输层对象
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Data
@Accessors(chain = true)
public class MemberAuthDTO {

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 会员名(openId、mobile)
     */
    private String username;

    /**
     * 状态(1:正常；0：禁用)
     */
    private Integer status;
}

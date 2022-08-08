package com.ctf.admin.pojo.po;

import lombok.Data;

import java.util.List;

/**
 * 认证用户持久层对象
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Data
public class UserAuthPO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户状态(1:正常;0:禁用)
     */
    private Integer status;

    /**
     * 用户角色编码集合 ["ROOT","ADMIN"]
     */
    private List<String> roles;

    /**
     * 部门ID
     */
    private Long deptId;

}

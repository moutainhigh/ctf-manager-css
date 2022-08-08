package com.ctf.common.constant;

/**
 * 全局常量
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public interface GlobalConstants {

    /**
     * 全局状态-是
     */
    Integer STATUS_YES = 1;

    /**
     * 超级管理员角色编码
     */
    String ROOT_ROLE_CODE = "ROOT";

    /**
     * [ {接口路径:[角色编码]},...]
     */
    String URL_PERM_ROLES_KEY = "permission:url";

    /**
     * [{按钮权限标识:[角色编码]},...]
     */
    String BTN_PERM_ROLES_KEY = "permission:btn";

}

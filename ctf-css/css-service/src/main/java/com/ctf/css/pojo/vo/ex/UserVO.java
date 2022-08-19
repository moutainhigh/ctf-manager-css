package com.ctf.css.pojo.vo.ex;

import lombok.Data;

/**
 * @Author zhangyizheng
 * @Date 2022/8/10 16:23
 * @Describe UserDTO
 */
@Data
public class UserVO {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 用户工号
     */
    private String staffCode;

    /**
     * 用户名
     */
    private String staffName;

    /**
     * 1:男，2:女
     */
    private Integer gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户手机号码
     */
    private String telphone;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 职位id(position)
     */
    private String positionId;
    /**
     * 所属部门(department）
     */
    private Long departmentId;
    /**
     * 领域名称
     */
    private String superviseDomainName;

    /**
     * 状态
     */
    private String status;
}

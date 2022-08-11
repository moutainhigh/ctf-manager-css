package com.ctf.css.pojo.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author zhangyizheng
 * @Date 2022/8/11 14:01
 * @Describe SuperviseForm
 */
@ApiModel(value = "督导人员表单信息")
@Data
public class SuperviseForm {
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

}

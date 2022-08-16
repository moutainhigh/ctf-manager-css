package com.ctf.css.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.ctf.common.base.BaseEntity;
import lombok.Data;

/**
 * 用户信息
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
public class UserInfo extends BaseEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 对应租户(tenant_info)
     */
    private Long tenantId;

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
     * 头像URL
     */
    private String avatar;

    /**
     * 职位id(position)
     */
    private String positionId;

    /**
     * 默认密码
     */
    private String defaultPwd;

    /**
     * 是否登录时修改密码;0_否,1_是
     */
    private String developerEditFlag;

    /**
     * 所属部门(department）
     */
    private Long departmentId;

    /**
     * 人员类型;对应的数据字典Id
     */
    private String officerCd;

    /**
     * 入职日期
     */
    private Date hireDt;

    /**
     * 离职日期
     */
    private Date terminationDt;

    /**
     * 在职状态;(A-在职，I-离职)
     */
    private String hrStatus;

    /**
     * 主管ID
     */
    private String supervisorId;

    /**
     * 主管级别;对应的数据字典Id
     */
    private String suplLvlId;

    /**
     * 公司;对应的数据字典Id
     */
    private String company;

    /**
     * 地区;对应的数据字典Id
     */
    private String country;

    /**
     * 地点;对应的数据字典Id
     */
    private String location;

    /**
     * 用户开发平台唯一标识
     */
    private String unionId;

    /**
     * 用户的APP标识
     */
    private String openId;

    /**
     * 创建人
     */
    private Long createdBy;


    /**
     * 更新人
     */
    private Long updatedBy;


    /**
     * 状态(1启用 0不启用)
     */
    private String status;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 删除状态;0-未删除 1已删除
     */
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;


}
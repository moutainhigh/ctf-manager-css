package com.ctf.auth.model.bo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ctf.auth.model.po.SysUserToWarehouse;
import com.ctf.common.entity.SysBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * SysUser 实体类
 *
 * @author jayud
 * @since 2022-02-21
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "后台用户表对象", description = "后台用户表")
public class SysUserForm extends SysBaseEntity {


    @ApiModelProperty(value = "登录名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "英文名")
    private String enUserName;

    @ApiModelProperty(value = "工号")
    private String numberName;

    @ApiModelProperty(value = "QQ号")
    private String qqNumber;

    @ApiModelProperty(value = "用户图像")
    private String headPortrait;

    @ApiModelProperty(value = "性别(0-默认未知,1-男,2-女)")
    private Integer sex;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "手机号码")
    private String cellPhoneNumber;

    @ApiModelProperty(value = "钉钉")
    private String nailingNumber;

    @ApiModelProperty(value = "微信号码")
    private String wechatNumber;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "是否部门负责人1-是 0-否")
    private Boolean isDepartCharge;

    @ApiModelProperty(value = "所属部门id")
    private Long departId;

    @ApiModelProperty(value = "所属部门id节点")
    private String departmentList;

    @ApiModelProperty(value = "负责部门ids")
    private String departIds;

    @ApiModelProperty(value = "多租户标识")
    private String relTenantIds;

    @ApiModelProperty(value = "岗位ID")
    private Long postId;

    @ApiModelProperty(value = "入职时间")
    private Date entryTime;

    @ApiModelProperty(value = "员工状态：0-离职，1-在职")
    private Integer jobStatus;

    @ApiModelProperty(value = "冻结状态：0-冻结，1-正常")
    private Integer status;

    @ApiModelProperty(value = "1-用户 2-客户 3-供应商")
    private String userType;

    @ApiModelProperty(value = "上司id")
    private Long supervisorId;

    @ApiModelProperty(value = "兼职部门(多个逗号隔开)")
    private String partTimeDepId;

    @ApiModelProperty(value = "租户编码")
    private String code;

    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "是否删除，0未删除，1已删除")
//    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    private List<String> creationTime;


    @ApiModelProperty(value = "角色id集合")
    private List<Long> roleIds;

    @ApiModelProperty(value = "用户id集合")
    private List<Long> userIds;

    @ApiModelProperty(value = "所属部门id集合")
    private List<Long> departIdLists;

    @ApiModelProperty(value = "新密码")
    private String newPassword;


    @ApiModelProperty(value = "是否仓库操作人员")
    private Boolean isWarehouseOperator;

    @ApiModelProperty(value = "仓库集合")
    private List<SysUserToWarehouseForm> sysUserToWarehouselist;


    @ApiModelProperty(value = "仓库关联数据")
    private List<SysUserToWarehouse> warehouseList;

    @ApiModelProperty(value = "当前所属仓库id节点集合")
    private List<Long> warehouseIdLists;
}

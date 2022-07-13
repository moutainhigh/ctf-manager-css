package com.ctf.auth.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ctf.auth.model.enums.ActionEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 角色数据权限
 * </p>
 *
 * @author LLJ
 * @since 2021-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleActionDataVO {

    @ApiModelProperty(value = "自动ID")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "权限ID")
    private Integer actionId;

    @ApiModelProperty(value = "权限CODE")
    private String actionCode;

    @ApiModelProperty(value = "菜单CODE")
    private String menuCode;

    @ApiModelProperty(value = "菜单名称")
    private String title;

    @ApiModelProperty(value = "权限类型（0无权限，1个人权限，2团队权限，3所有数据权限）")
    private Integer dateType;

    @ApiModelProperty(value = "权限类型（0无权限，1个人权限，2团队权限，3所有数据权限）")
    private String dateTypeName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人名称")
    private String crtByName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime crtByDtm;

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
        this.dateTypeName = ActionEnum.getDesc(dateType);
    }

}

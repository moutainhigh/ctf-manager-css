package com.ctf.auth.model.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ctf.common.entity.SysBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.experimental.Accessors;

/**
 * Reports 实体类
 *
 * @author jayud
 * @since 2022-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="对象", description="")
public class Reports extends SysBaseEntity {


    @ApiModelProperty(value = "模块ID")
    private Integer modId;

    @ApiModelProperty(value = "模块名称")
    private String modName;

    @ApiModelProperty(value = "报表显示名称")
    private String rptName;

    @ApiModelProperty(value = "报表数径")
    private String rptPath;

    @ApiModelProperty(value = "报表文件名")
    private String rptFileName;

    @ApiModelProperty(value = "数据源参数")
    private BigDecimal paraNum;

    @ApiModelProperty(value = "数据源名称")
    private String procName;

    @ApiModelProperty(value = "是否纵向或者横向，true为纵，false为横向")
    private String directionFlag;

    @ApiModelProperty(value = "是否有子报表,true：有，false没有")
    private String isChildFlag;

    @ApiModelProperty(value = "表报参数名，多个中间用英文逗号隔开")
    private String paraStr;

    @ApiModelProperty(value = "菜单名ID")
    private Integer menuId;

    @ApiModelProperty(value = "菜单名代码")
    private String menuCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "组织机构ID")
    private Integer orgId;

    @ApiModelProperty(value = "多租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "多租户code")
    private String tenantCode;

    @ApiModelProperty(value = "创建人")
    private Integer createUserId;



    @ApiModelProperty(value = "最后修改人")
    private Integer updateId;




    @ApiModelProperty(value = "删除标志")
    private Boolean isDeleted;

    @ApiModelProperty(value = "删除人")
    private Integer deletedUserId;

    @ApiModelProperty(value = "删除人名称")
    private String deleteUserName;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "删除时间")
    private LocalDateTime deleteTime;


}

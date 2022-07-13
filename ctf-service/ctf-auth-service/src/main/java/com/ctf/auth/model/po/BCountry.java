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

/**
 * BCountry 实体类
 *
 * @author jayud
 * @since 2022-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="国家表对象", description="国家表")
public class BCountry extends SysBaseEntity {


    @ApiModelProperty(value = "国家代码")
    private String codeNo;

    @ApiModelProperty(value = "国家中文名称")
    private String nameCh;

    @ApiModelProperty(value = "国家英文名称")
    private String nameEn;

    @ApiModelProperty(value = "过关海关编码")
    private String hsCode;

    @ApiModelProperty(value = "是否最惠国，0否，1是")
    private Integer favoredF;

    @ApiModelProperty(value = "是否疫区，0否，1是")
    private Integer affectedF;

    @ApiModelProperty(value = "国家国际代码")
    private String codeInter;

    @ApiModelProperty(value = "排序(越大排越前面)")
    private Integer orderNum;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人ID")
    private Integer crtBy;

    @ApiModelProperty(value = "创建人名称")
    private String crtByName;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime crtByDtm;

    @ApiModelProperty(value = "最后修改人ID")
    private Integer mdyBy;

    @ApiModelProperty(value = "最后修改人名称")
    private String mdyByName;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime mdyByDtm;

    @ApiModelProperty(value = "删除标记")
    private Integer voided;

    @ApiModelProperty(value = "删除人ID")
    private Integer voidedBy;

    @ApiModelProperty(value = "删除人名称")
    private String voidedByName;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "删除时间")
    private LocalDateTime voidedByDtm;

    @ApiModelProperty(value = "三字码")
    private String threeCharacterCode;


}

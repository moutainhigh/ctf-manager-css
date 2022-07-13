package com.ctf.auth.model.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ctf.common.entity.BasePageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QueryForm extends BasePageForm {

    @ApiModelProperty("搜索条件")
    private String condition;

    @ApiModelProperty("搜索key")
    private String key;

    @ApiModelProperty("模糊条件")
    private String name;

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("客户id")
    private Integer customerId;

    @ApiModelProperty("业务类型")
    private Integer modelType;

    @ApiModelProperty(value = "租户编码")
    private String tenantCode;

    @ApiModelProperty("权限code")
    private String actionCode;

    @ApiModelProperty("记录Id")
    private Integer recordId;

    //开票
    @ApiModelProperty(value = "发票代码")
    private String invcode;

    @ApiModelProperty(value = "增值税票号")
    private String invnumber;

    @ApiModelProperty(value = "开票日期")
    private String invdate;

    //开票信息
    @ApiModelProperty(value = "客户ID")
    @JsonProperty("cId")
    private Integer cId;

    @ApiModelProperty(value = "客户名称")
    @JsonProperty("cName")
    private String cName;

    @ApiModelProperty(value = "客户税号")
    @JsonProperty("cTaxno")
    private String cTaxno;

    @ApiModelProperty(value = "客户银行和账号")
    @JsonProperty("cBank")
    private String cBank;

    @ApiModelProperty(value = "客户地址和电话")
    @JsonProperty("cAddress")
    private String cAddress;

    //状态变更
    @ApiModelProperty(value = "状态")
    private String stateflage;

    @ApiModelProperty(value = "状态变更原因")
    private String changeReason;

}

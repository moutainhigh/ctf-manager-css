package com.zdf.auth.model.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.zdf.common.entity.SysBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.experimental.Accessors;

/**
 * CostInfo 实体类
 *
 * @author jayud
 * @since 2022-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CostInfo对象", description="费用名描述")
public class CostInfo extends SysBaseEntity {


    @ApiModelProperty(value = "费用code")
    private String idCode;

    @ApiModelProperty(value = "费用名")
    private String name;

    @ApiModelProperty(value = "费用名状态(0无效 1有效)")
    private String status;

    @ApiModelProperty(value = "费用类型(cost_type id)多个id用','隔开")
    private String cids;

    @ApiModelProperty(value = "描述")
    private String remarks;



    @ApiModelProperty(value = "状态(1应收 2应付)")
    private Integer types;



    @ApiModelProperty(value = "是否展示给司机")
    private Boolean isDriverShow;

    @ApiModelProperty(value = "实报实销")
    private Boolean isReimbursement;

    @ApiModelProperty(value = "开票名称")
    private String invName;

    @ApiModelProperty(value = "0 ：进口13点税票， 1：进口6个点的税票，2：应收外汇，3：出口应收代理费，4：转口应收，5：应收账期费，6：国内贸易应收款，7：汇差，8：其他费用, 9：增值服务，10：应收账期费，11：应收逾期费")
    private String landTaxFlag;

    @ApiModelProperty(value = "币别")
    private String currencyName;

}

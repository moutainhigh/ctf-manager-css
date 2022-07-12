package com.zdf.auth.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 费用名描述
 * </p>
 *
 * @author
 * @since
 */
@Data
public class CostInfoVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    private Long id;

    @ApiModelProperty(value = "费用code")
    private String idCode;

    @ApiModelProperty(value = "费用名")
    private String name;

    @ApiModelProperty(value = "费用名状态(0禁用 1启用)")
    private String status;

    @ApiModelProperty(value = "费用类别")
    private List<Long> cids;

    @ApiModelProperty(value = "描述")
    private String remarks;

    @ApiModelProperty(value = "费用类型")
    private String codeName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateUser;

    @ApiModelProperty(value = "币别")
    private String currencyName;

    @ApiModelProperty(value = "是否展示给司机")
    private Boolean isDriverShow;

    @ApiModelProperty(value = "实报实销")
    private Boolean isReimbursement;

    @ApiModelProperty(value = "开票名称")
    private String invName;

    @ApiModelProperty(value = "0 ：进口13点税票， 1：进口6个点的税票，2：应收外汇，3：出口应收代理费，4：转口应收，5：应收账期费，6：国内贸易应收款，7：汇差，8：其他费用, 9：增值服务，10：应收账期费，11：应收逾期费")
    private String landTaxFlag;

}

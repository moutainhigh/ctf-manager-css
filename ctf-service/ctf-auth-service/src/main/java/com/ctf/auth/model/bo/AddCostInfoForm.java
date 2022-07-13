package com.ctf.auth.model.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * 费用名描述
 * </p>
 *
 * @author 李达荣
 * @since 2020-10-27
 */
@Data
public class AddCostInfoForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID,修改时必传")
    private Long id;

    @ApiModelProperty(value = "费用code", required = true)
    @NotEmpty(message = "idCode is required")
    private String idCode;

    @ApiModelProperty(value = "费用名", required = true)
    @NotEmpty(message = "name is required")
    private String name;

    @ApiModelProperty(value = "描述")
    private String remarks;

    @ApiModelProperty(value = "费用类型id集合", required = true)
    private List<Long> cids;

    @ApiModelProperty(value = "开票名称")
    private String invName;

    @ApiModelProperty(value = "0 ：进口13点税票， 1：进口6个点的税票，2：应收外汇，3：出口应收代理费，4：转口应收，5：应收账期费，6：国内贸易应收款，7：汇差，8：其他费用, 9：增值服务，10：应收账期费，11：应收逾期费")
    private String landTaxFlag;

    @ApiModelProperty(value = "币别")
    private String currencyName;
//    @ApiModelProperty(value = "费用类别")
//    @NotNull(message = "cids is required")
//    private List<CostTypeVO> costTypeVOs;


}

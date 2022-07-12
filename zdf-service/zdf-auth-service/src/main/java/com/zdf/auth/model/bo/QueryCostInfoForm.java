package com.zdf.auth.model.bo;

import com.zdf.common.entity.BasePageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 费用名描述
 * </p>
 *
 * @author
 * @since 2020-10-27
 */
@Data
@ApiModel(value="CostInfo对象", description="费用名描述")
public class QueryCostInfoForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "费用名称代码")
    private String idCode;

    @ApiModelProperty(value = "费用名")
    private String name;

    @ApiModelProperty(value = "费用类别")
    private String costType;

    @ApiModelProperty(value = "状态")
    private String status;
}

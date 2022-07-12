package com.zdf.auth.model.bo;

import com.zdf.common.entity.BasePageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 费用类型，暂时废弃
 * </p>
 *
 * @author 李达荣
 * @since 2020-10-27
 */
@Data
public class QueryCostTypeForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "费用类别代码")
    private String code;

    @ApiModelProperty(value = "费用类别")
    private String codeName;

    @ApiModelProperty(value = "状态 0禁用 1启用")
    private String status;

}

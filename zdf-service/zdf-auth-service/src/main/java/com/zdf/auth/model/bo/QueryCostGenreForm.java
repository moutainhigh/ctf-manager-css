package com.zdf.auth.model.bo;

import com.zdf.common.entity.BasePageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 基础数据费用类型
 * </p>
 *
 * @author
 * @since
 */
@Data
public class QueryCostGenreForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "费用类型代码")
    private String code;

    @ApiModelProperty(value = "费用类型名称")
    private String name;

    @ApiModelProperty(value = "状态（1启用，0禁用）")
    private String status;

}

package com.ctf.auth.model.bo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 费用类型
 * </p>
 *
 * @author
 * @since
 */
@Data
public class AddCostTypeForm extends Model<AddCostTypeForm> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID,修改时必传")
    private Long id;

    @ApiModelProperty(value = "费用类别代码", required = true)
    @NotEmpty(message = "code is required")
    private String code;

    @ApiModelProperty(value = "费用类别", required = true)
    @NotEmpty(message = "codeName is required")
    private String codeName;

    @ApiModelProperty(value = "是否代垫代收", required = true)
    @NotNull(message = "isPayCollection is required")
    private Boolean isPayCollection;

    @ApiModelProperty(value = "描述")
    private String remarks;

}

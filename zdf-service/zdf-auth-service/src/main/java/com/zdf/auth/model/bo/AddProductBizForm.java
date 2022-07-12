package com.zdf.auth.model.bo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zdf.auth.model.vo.CostGenreVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * 产品服务对应业务类型
 * </p>
 *
 * @author chuanmei
 * @since 2020-09-15
 */
@Data
public class AddProductBizForm extends Model<AddProductBizForm> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID,修改时必传")
    private Long id;

    @ApiModelProperty(value = "编码", required = true)
    @NotEmpty(message = "idCode is required")
    private String idCode;

    @ApiModelProperty(value = "业务名", required = true)
    @NotEmpty(message = "name is required")
    private String name;

    @ApiModelProperty(value = "费用类型集合")
    private List<CostGenreVO> costGenreVOs;

    @ApiModelProperty(value = "默认费用类型")
    private Long costGenreDefault;

    @ApiModelProperty(value = "描述")
    private String remarks;


}

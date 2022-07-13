package com.ctf.auth.model.bo;

import com.ctf.common.entity.BasePageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QuerySystemSqlConfigForm extends BasePageForm {

    @ApiModelProperty(value = "SQL代码")
    private String sqlCode;
}

package com.ctf.utils;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class FrontTitleAndItemVo {

    List<FrontEndVo> frontEndVos;

    List<?> vos ;
}

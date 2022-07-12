package com.zdf.ocean.model.bo;

import com.zdf.common.entity.SysBaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  Mapper 示例代码
 * @author liwei
 * @since 2022-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "合同报价详情对象", description = "合同报价详情")
public class AddCrmContractQuotationDetailsForm extends SysBaseEntity {
}

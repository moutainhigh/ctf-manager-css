package com.zdf.ocean.model.po;

import lombok.Data;
import com.zdf.common.entity.SysBaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *  Mapper 示例代码
 * @author liwei
 * @since 2022-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "合同报价对象", description = "合同报价")
public class CrmContractQuotation extends SysBaseEntity {
}

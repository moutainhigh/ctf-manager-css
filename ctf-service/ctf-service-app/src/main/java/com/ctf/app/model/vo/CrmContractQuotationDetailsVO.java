package com.ctf.app.model.vo;

import com.ctf.common.entity.SysBaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
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
@ApiModel(value="合同报价详情对象", description="合同报价详情")
public class CrmContractQuotationDetailsVO extends SysBaseEntity {

}

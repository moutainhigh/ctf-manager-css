package com.ctf.css.pojo.query;

import com.ctf.common.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author zhangyizheng
 * @Date 2022/8/11 17:39
 * @Describe SupervisorDomainPageQuery
 */
@ApiModel("督导领域分页查询对象")
@Data
public class SupervisorDomainPageQuery extends BasePageQuery {
    /**
     * 领域名称
     */
    private String superviseDomainName;
    /**
     * 状态(1:启用 0:禁用)
     */
    private String status;
}

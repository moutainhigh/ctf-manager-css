package com.ctf.css.pojo.query;

import com.ctf.common.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author zhangyizheng
 * @Date 2022/8/10 15:15
 * @Describe TourSupervisorPageQuery
 */
@ApiModel("督导人员分页查询对象")
@Data
public class TourSupervisorPageQuery extends BasePageQuery {

    /**
     * 用户工号
     */
    private String staffCode;

    /**
     * 用户名
     */
    private String staffName;
    /**
     * 领域名称
     */
    private String superviseDomainName;

}

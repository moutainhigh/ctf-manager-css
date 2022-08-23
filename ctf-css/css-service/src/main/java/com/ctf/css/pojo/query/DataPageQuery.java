package com.ctf.css.pojo.query;

import com.ctf.common.base.BasePageQuery;
import lombok.Data;

/**
 * @Author zhangyizheng
 * @Date 2022/8/23 14:05
 * @Describe DataPageQuery
 */
@Data
public class DataPageQuery extends BasePageQuery {

    /**
     * 领域名称
     */
    private String superviseDomainName;

    /**
     * 方案名称
     */
    private String schemeName;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 督导
     */
    private String supervisor;

    /**
     * 关键字；方案编号或名称
     */
    private String keywords;
}

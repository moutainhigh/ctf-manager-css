package com.ctf.pms.pojo.entity;

import com.ctf.common.base.BaseEntity;
import lombok.Data;

/**
 * 分类品牌
 *
 * @author H.m
 * @date 2022/7/2
 */
@Data
public class PmsCategoryBrand extends BaseEntity {
    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 品牌ID
     */
    private Long brandId;

}

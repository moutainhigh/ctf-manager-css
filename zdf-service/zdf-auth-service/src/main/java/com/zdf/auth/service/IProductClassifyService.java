package com.zdf.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zdf.auth.model.po.ProductClassify;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author chuanmei
 * @since 2020-09-15
 */
public interface IProductClassifyService extends IService<ProductClassify> {

    List<ProductClassify> findProductClassify(Map<String, Object> param);

    /**
     * 查询所有启用的父类产品分类
     */
    List<ProductClassify> getEnableParentProductClassify(String status);

    /**
     * 根据id集合查询所有产品分类
     */
    List<ProductClassify> getByIds(List<Long> ids);

    /**
     * 根据产品名字查询产品id
     */
    ProductClassify getProductClassifyId(String str);


    /**
     * 根据产品编码集合查询产品
     */
    List<ProductClassify> getIdCodes(List<String> idCodes);

    /**
     * 根据条件查询产品信息
     *
     * @param productClassify
     * @return
     */
    List<ProductClassify> getByCondition(ProductClassify productClassify);
}

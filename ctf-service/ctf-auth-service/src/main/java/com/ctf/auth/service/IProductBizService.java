package com.ctf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.auth.model.bo.AddProductBizForm;
import com.ctf.auth.model.bo.QueryProductBizForm;
import com.ctf.auth.model.po.ProductBiz;
import com.ctf.auth.model.vo.ProductBizVO;

import java.util.List;

/**
 * 业务类型
 */
public interface IProductBizService extends IService<ProductBiz> {

    List<ProductBiz> findProductBiz();

    /**
     * 获取作业类型
     *
     * @param idCode
     * @return
     */
    ProductBiz getProductBizByCode(String idCode);


    /**
     * 列表分页查询
     *
     * @param form
     * @return
     */
    IPage<ProductBizVO> findProductBizByPage(QueryProductBizForm form);


    /**
     * 根据id查询业务类型
     */
    ProductBizVO getById(Long id);

    /**
     * 新增编辑业务类型
     *
     * @param form
     * @return
     */
    boolean saveOrUpdateProductBiz(AddProductBizForm form);


    /**
     * 更改启用/禁用业务类型状态
     *
     * @param id
     * @return
     */
    boolean enableOrDisableProductBiz(Long id);

    /**
     * 校验唯一性
     *
     * @return
     */
    boolean checkUnique(ProductBiz productBiz);

    /**
     * 获取启用业务类型
     */
    public List<ProductBiz> getEnableProductBiz();

    /**
     * 根据业务名称获取业务编码
     * @param name
     * @return
     */
    String getProductBizIdCodeByName(String name);
}

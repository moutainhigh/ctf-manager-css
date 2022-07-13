package com.ctf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.auth.model.po.BBrand;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 品牌库表 服务类
 *
 * @author jayud
 * @since 2022-04-22
 */
public interface IBBrandService extends IService<BBrand> {


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-22
     * @param: bBrand
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.BBrand>
     **/
    IPage<BBrand> selectPage(BBrand bBrand,
                                Integer currentPage,
                                Integer pageSize,
                                HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-04-22
     * @param: bBrand
     * @param: req
     * @return: java.util.List<com.ctf.auth.model.po.BBrand>
     **/
    List<BBrand> selectList(BBrand bBrand);



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-04-22
     * @param: id
     * @return: void
     **/
    void phyDelById(Long id);


    /**
    * @description 逻辑删除
    * @author  jayud
    * @date   2022-04-22
    * @param: id
    * @return: com.ctf.component.commons.result.Result
    **/
    void logicDel(Long id);



    /**
     * @description 查询导出
     * @author  jayud
     * @date   2022-04-22
     * @param: queryReceiptForm
     * @param: req
     * @return: java.util.List<java.util.LinkedHashMap<java.lang.String,java.lang.Object>>
     **/
    List<LinkedHashMap<String, Object>> queryBBrandForExcel(Map<String, Object> paramMap);


}

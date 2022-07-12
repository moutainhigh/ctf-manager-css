package com.zdf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdf.auth.model.po.CurrencyInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 币种 服务类
 *
 * @author jayud
 * @since 2022-04-11
 */
public interface ICurrencyInfoService extends IService<CurrencyInfo> {


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-11
     * @param: currencyInfo
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.CurrencyInfo>
     **/
    IPage<CurrencyInfo> selectPage(CurrencyInfo currencyInfo,
                                Integer currentPage,
                                Integer pageSize,
                                HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-04-11
     * @param: currencyInfo
     * @param: req
     * @return: java.util.List<com.zdf.auth.model.po.CurrencyInfo>
     **/
    List<CurrencyInfo> selectList(CurrencyInfo currencyInfo);



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-04-11
     * @param: id
     * @return: void
     **/
    void phyDelById(Long id);


    /**
    * @description 逻辑删除
    * @author  jayud
    * @date   2022-04-11
    * @param: id
    * @return: com.jyd.component.commons.result.Result
    **/
    void logicDel(Long id);



    /**
     * @description 查询导出
     * @author  jayud
     * @date   2022-04-11
     * @param: queryReceiptForm
     * @param: req
     * @return: java.util.List<java.util.LinkedHashMap<java.lang.String,java.lang.Object>>
     **/
    List<LinkedHashMap<String, Object>> queryCurrencyInfoForExcel(Map<String, Object> paramMap);

    /**
     * 获取币种信息
     *
     * @param createdTimeStr
     * @return
     */
    List<CurrencyInfo> findCurrencyInfo(String createdTimeStr);
}

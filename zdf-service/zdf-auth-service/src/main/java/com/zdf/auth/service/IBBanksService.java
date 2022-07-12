package com.zdf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdf.auth.model.po.BBanks;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 公司银行账户 服务类
 *
 * @author jayud
 * @since 2022-04-22
 */
public interface IBBanksService extends IService<BBanks> {


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-22
     * @param: bBanks
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.BBanks>
     **/
    IPage<BBanks> selectPage(BBanks bBanks,
                                Integer currentPage,
                                Integer pageSize,
                                HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-04-22
     * @param: bBanks
     * @param: req
     * @return: java.util.List<com.zdf.auth.model.po.BBanks>
     **/
    List<BBanks> selectList(BBanks bBanks);



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
    * @return: com.jyd.component.commons.result.Result
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
    List<LinkedHashMap<String, Object>> queryBBanksForExcel(Map<String, Object> paramMap);


}

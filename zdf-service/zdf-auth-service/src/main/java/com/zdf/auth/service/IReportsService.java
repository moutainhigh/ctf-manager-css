package com.zdf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdf.auth.model.bo.AddReportsForm;
import com.zdf.auth.model.po.Reports;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zdf.auth.model.vo.ReportsVO;
import com.zdf.common.BaseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *  服务类
 *
 * @author jayud
 * @since 2022-04-25
 */
public interface IReportsService extends IService<Reports> {


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-25
     * @param: reports
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.Reports>
     **/
    IPage<Reports> selectPage(Reports reports,
                                Integer currentPage,
                                Integer pageSize,
                                HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-04-25
     * @param: reports
     * @param: req
     * @return: java.util.List<com.zdf.auth.model.po.Reports>
     **/
    List<Reports> selectList(Reports reports);



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-04-25
     * @param: id
     * @return: void
     **/
    void phyDelById(Long id);


    /**
    * @description 逻辑删除
    * @author  jayud
    * @date   2022-04-25
    * @param: id
    * @return: com.jyd.component.commons.result.Result
    **/
    void logicDel(List<Long> id);



    /**
     * @description 查询导出
     * @author  jayud
     * @date   2022-04-25
     * @param: queryReceiptForm
     * @param: req
     * @return: java.util.List<java.util.LinkedHashMap<java.lang.String,java.lang.Object>>
     **/
    List<LinkedHashMap<String, Object>> queryReportsForExcel(Map<String, Object> paramMap);

    /**
     * 新增或修改报表设计
     * @param form 新增实体
     * @return
     */
    boolean saveOrUpdateReports(AddReportsForm form);

    /**
     * 根据菜单code获取报表设计
     * @param actionCode 菜单code
     * @return
     */
    List<ReportsVO> getReportsByMenuCode(String actionCode);

    /**
     * 获取新增下拉
     * @return
     */
    BaseResult getCheckData();
}

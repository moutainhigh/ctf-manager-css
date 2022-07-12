package com.zdf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdf.auth.model.bo.AddCostInfoForm;
import com.zdf.auth.model.bo.QueryCostInfoForm;
import com.zdf.auth.model.po.CostInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zdf.auth.model.po.CostType;
import com.zdf.auth.model.vo.CostInfoVO;
import com.zdf.common.entity.InitComboxStrVO;
import com.zdf.common.entity.InitComboxVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 费用名描述 服务类
 *
 * @author jayud
 * @since 2022-04-11
 */
public interface ICostInfoService extends IService<CostInfo> {


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-11
     * @param: costInfo
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.CostInfo>
     **/
    IPage<CostInfo> selectPage(CostInfo costInfo,
                                Integer currentPage,
                                Integer pageSize,
                                HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-04-11
     * @param: costInfo
     * @param: req
     * @return: java.util.List<com.zdf.auth.model.po.CostInfo>
     **/
    List<CostInfo> selectList(CostInfo costInfo);



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
    List<LinkedHashMap<String, Object>> queryCostInfoForExcel(Map<String, Object> paramMap);

    /**
     * 获取费用类型
     *
     * @return
     */
    List<CostInfo> findCostInfo();

    /**
     * 根据费用名称查询费用类型
     * @return
     */
    Map<String, List<InitComboxVO>> initCostTypeByCostInfoCode();

    List<CostInfo> getCostInfoByStatus(String status);

    /**
     * 列表分页查询
     *
     * @param form
     * @return
     */
    IPage<CostInfoVO> findCostInfoByPage(QueryCostInfoForm form);

    /**
     * 新增编辑费用名称
     *
     * @param form
     * @return
     */
    boolean saveOrUpdateCostInfo(AddCostInfoForm form);

    /**
     * 根据id查询费用名称
     */
//    CostInfoVO getById(Long id);

    /**
     * 更改启用/禁用状态
     *
     * @param id
     * @return
     */
    boolean enableOrDisableCostInfo(Long id);

    /**
     * 校验唯一性
     *
     * @return
     */
    boolean checkUnique(CostInfo costInfo);

    /**
     * 下拉根据费用类别名称查询费用名称
     *
     * @return
     */
    List<InitComboxStrVO> getCostInfoByCostTypeName(String costTypeName);

    /**
     * 下拉根据费用类别code查询费用名称
     *
     * @return
     */
    List<InitComboxStrVO> getCostInfoByCostTypeCode(String costTypeCde);

    /**
     * 下拉根据费用类别查询费用名称
     *
     * @return
     */
    public List<InitComboxStrVO> getCostInfoByCostType(List<CostType> costTypes);

    /**
     * 根据code获取费用名称
     * @param costCode
     * @return
     */
    String getCostNameByCostCode(String costCode);

}

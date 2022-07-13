package com.ctf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.auth.model.bo.AddCostTypeForm;
import com.ctf.auth.model.bo.QueryCostTypeForm;
import com.ctf.auth.model.po.CostType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.auth.model.vo.CostTypeVO;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 费用类别 服务类
 *
 * @author jayud
 * @since 2022-04-11
 */
public interface ICostTypeService extends IService<CostType> {


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-11
     * @param: costType
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.CostType>
     **/
    IPage<CostType> selectPage(CostType costType,
                                Integer currentPage,
                                Integer pageSize,
                                HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-04-11
     * @param: costType
     * @param: req
     * @return: java.util.List<com.ctf.auth.model.po.CostType>
     **/
    List<CostType> selectList(CostType costType);



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
    * @return: com.ctf.component.commons.result.Result
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
    List<LinkedHashMap<String, Object>> queryCostTypeForExcel(Map<String, Object> paramMap);

    /**
     * 查询所有启用费用类别
     * @return
     */
    List<CostType> getEnableCostType();

    List<CostType> getByCondition(CostType setCodeName);

    /**
     * 列表分页查询
     *
     * @param form
     * @return
     */
    IPage<CostTypeVO> findCostTypeByPage(QueryCostTypeForm form);

    /**
     * 根据id集合查询费用类别
     */
    List<CostTypeVO> getCostTypeByIds(List<Long> ids);

    /**
     * 新增编辑费用类别
     *
     * @param form
     * @return
     */
    boolean saveOrUpdateCostType(AddCostTypeForm form);

    /**
     * 更改启用/禁用状态
     * @param id
     * @return
     */
    boolean enableOrDisableCostType(Long id);

    /**
     * 校验唯一性
     * @return
     */
    boolean checkUnique(CostType costType);
}

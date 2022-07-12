package com.zdf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdf.auth.model.bo.AddCostGenreForm;
import com.zdf.auth.model.bo.QueryCostGenreForm;
import com.zdf.auth.model.po.CostGenre;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zdf.auth.model.vo.CostGenreVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础数据费用类型 服务类
 *
 * @author jayud
 * @since 2022-04-11
 */
public interface ICostGenreService extends IService<CostGenre> {


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-11
     * @param: costGenre
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.CostGenre>
     **/
    IPage<CostGenre> selectPage(CostGenre costGenre,
                                Integer currentPage,
                                Integer pageSize,
                                HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-04-11
     * @param: costGenre
     * @param: req
     * @return: java.util.List<com.zdf.auth.model.po.CostGenre>
     **/
    List<CostGenre> selectList(CostGenre costGenre);



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
    List<LinkedHashMap<String, Object>> queryCostGenreForExcel(Map<String, Object> paramMap);

    /**
     * 分页查询费用类型
     */
    IPage<CostGenreVO> findCostGenreByPage(QueryCostGenreForm form);

    /**
     * 新增编辑费用类型
     */
    boolean saveOrUpdateCostGenre(AddCostGenreForm form);

    /**
     * 更改启用/禁用费用类型状态
     * @param id
     * @return
     */
    public boolean enableOrDisableCostGenre(Long id);

    /**
     * 根据id查询费用类型
     */
    CostGenreVO getById(Long id);

    /**
     * 根据id集合查询费用类型
     */
    List<CostGenre> getByIds(List<Long> ids);

    /**
     * 获取启用费用类型
     */
    List<CostGenre> getEnableCostGenre();

    /**
     * 校验费用类型唯一性
     * @return
     */
    boolean checkUnique(CostGenre costGenre);

    /**
     * 新增或修改
     * @param form
     * @return
     */
    boolean saveOrUpdate(AddCostGenreForm form);

}

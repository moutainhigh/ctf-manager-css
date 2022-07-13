package com.ctf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.auth.model.bo.SysUserToWarehouseForm;
import com.ctf.auth.model.po.SysUserToWarehouse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.common.BaseResult;
import com.ctf.wms.model.po.Warehouse;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户与仓库关联表 服务类
 *
 * @author jayud
 * @since 2022-04-08
 */
public interface ISysUserToWarehouseService extends IService<SysUserToWarehouse> {


    /**
     * @description 分页查询
     * @author jayud
     * @date 2022-04-08
     * @param: sysUserToWarehouse
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.SysUserToWarehouse>
     **/
    IPage<SysUserToWarehouse> selectPage(SysUserToWarehouse sysUserToWarehouse,
                                         Integer currentPage,
                                         Integer pageSize,
                                         HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author jayud
     * @date 2022-04-08
     * @param: sysUserToWarehouse
     * @param: req
     * @return: java.util.List<com.ctf.auth.model.po.SysUserToWarehouse>
     **/
    List<SysUserToWarehouse> selectList(SysUserToWarehouse sysUserToWarehouse);


    /**
     * @description 物理删除
     * @author jayud
     * @date 2022-04-08
     * @param: id
     * @return: void
     **/
    void phyDelById(Long id);


    /**
     * @description 逻辑删除
     * @author jayud
     * @date 2022-04-08
     * @param: id
     * @return: com.ctf.component.commons.result.Result
     **/
    void logicDel(Long id);


    /**
     * @description 查询导出
     * @author jayud
     * @date 2022-04-08
     * @param: queryReceiptForm
     * @param: req
     * @return: java.util.List<java.util.LinkedHashMap < java.lang.String, java.lang.Object>>
     **/
    List<LinkedHashMap<String, Object>> querySysUserToWarehouseForExcel(Map<String, Object> paramMap);

    /**
     * @description 保存仓库数据
     * @author ciro
     * @date 2022/4/9 13:33
     * @param: userId
     * @param: warehouseList
     * @return: com.ctf.common.BaseResult
     **/
    BaseResult saveWarehouse(Long userId, List<SysUserToWarehouse> warehouseList);

    /**
     * 保存仓库数据
     *
     * @param userId
     * @param sysUserToWarehouselist
     * @param warehouseIdLists
     * @return
     */
    BaseResult saveUpdateWarehouse(Long userId, List<SysUserToWarehouseForm> sysUserToWarehouselist, List<Long> warehouseIdLists);

    /**
     * @description 获取仓库数据
     * @author ciro
     * @date 2022/4/9 14:06
     * @param:
     * @return: java.util.List<com.ctf.auth.model.po.SysUserToWarehouse>
     **/
    List<SysUserToWarehouse> getWarehouseMsg();

}

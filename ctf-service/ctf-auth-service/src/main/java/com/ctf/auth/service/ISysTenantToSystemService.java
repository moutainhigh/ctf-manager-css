package com.ctf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.auth.model.bo.SysTenantForm;
import com.ctf.auth.model.po.SysTenantToSystem;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 租户-系统关联表 服务类
 *
 * @author jayud
 * @since 2022-02-21
 */
public interface ISysTenantToSystemService extends IService<SysTenantToSystem> {


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-02-21
     * @param: sysTenantToSystem
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.SysTenantToSystem>
     **/
    IPage<SysTenantToSystem> selectPage(SysTenantToSystem sysTenantToSystem,
                                Integer currentPage,
                                Integer pageSize,
                                HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-02-21
     * @param: sysTenantToSystem
     * @param: req
     * @return: java.util.List<com.ctf.auth.model.po.SysTenantToSystem>
     **/
    List<SysTenantToSystem> selectList(SysTenantToSystem sysTenantToSystem);



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-02-21
     * @param: id
     * @return: void
     **/
    void phyDelById(Long id);


    /**
    * @description 逻辑删除
    * @author  jayud
    * @date   2022-02-21
    * @param: id
    * @return: com.ctf.component.commons.result.Result
    **/
    void logicDel(Long id);

    /**
     * @description 保存租户和系统关系
     * @author  ciro
     * @date   2022/2/22 11:32
     * @param: sysTenantForm
     * @return: void
     **/
    void saveTenantSystemRelation(SysTenantForm sysTenantForm);



}

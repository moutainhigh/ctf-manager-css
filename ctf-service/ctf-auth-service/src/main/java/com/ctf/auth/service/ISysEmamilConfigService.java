package com.ctf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctf.auth.model.po.SysEmamilConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.common.BaseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统邮箱配置表 服务类
 *
 * @author jayud
 * @since 2022-02-24
 */
public interface ISysEmamilConfigService extends IService<SysEmamilConfig> {


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-02-24
     * @param: sysEmamilConfig
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.SysEmamilConfig>
     **/
    IPage<SysEmamilConfig> selectPage(SysEmamilConfig sysEmamilConfig,
                                Integer currentPage,
                                Integer pageSize,
                                HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-02-24
     * @param: sysEmamilConfig
     * @param: req
     * @return: java.util.List<com.ctf.auth.model.po.SysEmamilConfig>
     **/
    List<SysEmamilConfig> selectList(SysEmamilConfig sysEmamilConfig);



    /**
     * @description 物理删除
     * @author  jayud
     * @date   2022-02-24
     * @param: id
     * @return: void
     **/
    void phyDelById(Long id);


    /**
    * @description 逻辑删除
    * @author  jayud
    * @date   2022-02-24
    * @param: id
    * @return: com.ctf.component.commons.result.Result
    **/
    void logicDel(Long id);

    /**
     * @description 保存数据
     * @author  ciro
     * @date   2022/2/24 12:33
     * @param: sysEmamilConfig
     * @return: com.ctf.common.BaseResult
     **/
    BaseResult saveConfig(SysEmamilConfig sysEmamilConfig);

    /**
     * @description 根据租户编码查询
     * @author  ciro
     * @date   2022/2/24 12:49
     * @param: tenantCode
     * @return: com.ctf.common.BaseResult<com.ctf.auth.model.po.SysEmamilConfig>
     **/
    BaseResult<SysEmamilConfig> selectByTenantCode(String tenantCode);



}

package com.zdf.auth.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdf.auth.model.po.SysEnterpriseDingdingConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zdf.common.BaseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统钉钉配置表 服务类
 *
 * @author jayud
 * @since 2022-02-24
 */
public interface ISysEnterpriseDingdingConfigService extends IService<SysEnterpriseDingdingConfig> {


    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-02-24
     * @param: sysEnterpriseDingdingConfig
     * @param: currentPage
     * @param: pageSize
     * @param: req
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.SysEnterpriseDingdingConfig>
     **/
    IPage<SysEnterpriseDingdingConfig> selectPage(SysEnterpriseDingdingConfig sysEnterpriseDingdingConfig,
                                Integer currentPage,
                                Integer pageSize,
                                HttpServletRequest req);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-02-24
     * @param: sysEnterpriseDingdingConfig
     * @param: req
     * @return: java.util.List<com.zdf.auth.model.po.SysEnterpriseDingdingConfig>
     **/
    List<SysEnterpriseDingdingConfig> selectList(SysEnterpriseDingdingConfig sysEnterpriseDingdingConfig);



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
    * @return: com.jyd.component.commons.result.Result
    **/
    void logicDel(Long id);

    /**
     * @description 保存数据
     * @author  ciro
     * @date   2022/2/24 12:52
     * @param: sysEnterpriseDingdingConfig
     * @return: com.zdf.common.BaseResult
     **/
    BaseResult saveConfig(SysEnterpriseDingdingConfig sysEnterpriseDingdingConfig);

    /**
     * @description 根据租户编码查询
     * @author  ciro
     * @date   2022/2/24 12:52
     * @param: tenantCode
     * @return: com.zdf.common.BaseResult<com.zdf.auth.model.po.SysEnterpriseDingdingConfig>
     **/
    BaseResult<SysEnterpriseDingdingConfig> selectByTenantCode(String tenantCode);


}

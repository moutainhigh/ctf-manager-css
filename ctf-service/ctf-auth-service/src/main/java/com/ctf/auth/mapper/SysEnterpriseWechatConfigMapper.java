package com.ctf.auth.mapper;

import com.ctf.auth.model.po.SysEnterpriseWechatConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统企业微信配置表 Mapper 接口
 *
 * @author jayud
 * @since 2022-02-24
 */
@Mapper
public interface SysEnterpriseWechatConfigMapper extends BaseMapper<SysEnterpriseWechatConfig> {

    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-02-24
     * @param: page
     * @param: sysEnterpriseWechatConfig
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.SysEnterpriseWechatConfig>
     **/
    IPage<SysEnterpriseWechatConfig> pageList(@Param("page") Page<SysEnterpriseWechatConfig> page, @Param("sysEnterpriseWechatConfig") SysEnterpriseWechatConfig sysEnterpriseWechatConfig);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-02-24
     * @param: sysEnterpriseWechatConfig
     * @return: java.util.List<com.ctf.auth.model.po.SysEnterpriseWechatConfig>
     **/
    List<SysEnterpriseWechatConfig> list(@Param("sysEnterpriseWechatConfig") SysEnterpriseWechatConfig sysEnterpriseWechatConfig);


    /**
     * @description 根据id物理删除
     * @author  jayud
     * @date   2022-02-24
     * @param: id
     * @return: int
     **/
    int phyDelById(@Param("id") Long id);

    /**
     * @description 根据id逻辑删除
     * @author  jayud
     * @date   2022-02-24
     * @param: id
     * @param: username
     * @return: int
     **/
    int logicDel(@Param("id") Long id,@Param("username") String username);
}

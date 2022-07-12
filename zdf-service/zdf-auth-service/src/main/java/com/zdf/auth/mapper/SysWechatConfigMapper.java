package com.zdf.auth.mapper;

import com.zdf.auth.model.po.SysWechatConfig;
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
 * 系统微信配置表 Mapper 接口
 *
 * @author jayud
 * @since 2022-02-24
 */
@Mapper
public interface SysWechatConfigMapper extends BaseMapper<SysWechatConfig> {

    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-02-24
     * @param: page
     * @param: sysWechatConfig
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.SysWechatConfig>
     **/
    IPage<SysWechatConfig> pageList(@Param("page") Page<SysWechatConfig> page, @Param("sysWechatConfig") SysWechatConfig sysWechatConfig);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-02-24
     * @param: sysWechatConfig
     * @return: java.util.List<com.zdf.auth.model.po.SysWechatConfig>
     **/
    List<SysWechatConfig> list(@Param("sysWechatConfig") SysWechatConfig sysWechatConfig);


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

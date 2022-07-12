package com.zdf.auth.mapper;

import com.zdf.auth.model.po.BBrand;
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
 * 品牌库表 Mapper 接口
 *
 * @author jayud
 * @since 2022-04-22
 */
@Mapper
public interface BBrandMapper extends BaseMapper<BBrand> {

    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-22
     * @param: page
     * @param: bBrand
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.BBrand>
     **/
    IPage<BBrand> pageList(@Param("page") Page<BBrand> page, @Param("bBrand") BBrand bBrand);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-04-22
     * @param: bBrand
     * @return: java.util.List<com.zdf.auth.model.po.BBrand>
     **/
    List<BBrand> list(@Param("bBrand") BBrand bBrand);


    /**
     * @description 根据id物理删除
     * @author  jayud
     * @date   2022-04-22
     * @param: id
     * @return: int
     **/
    int phyDelById(@Param("id") Long id);

    /**
     * @description 根据id逻辑删除
     * @author  jayud
     * @date   2022-04-22
     * @param: id
     * @param: username
     * @return: int
     **/
    int logicDel(@Param("id") Long id,@Param("username") String username);


    /**
     * @description 查询导出
     * @author  jayud
     * @date   2022-04-22
     * @param: paramMap
     * @return: java.util.List<java.util.LinkedHashMap<java.lang.String,java.lang.Object>>
     **/
    List<LinkedHashMap<String, Object>> queryBBrandForExcel(Map<String, Object> paramMap);
}

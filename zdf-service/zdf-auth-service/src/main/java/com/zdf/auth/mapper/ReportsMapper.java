package com.zdf.auth.mapper;

import com.zdf.auth.model.po.Reports;
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
 *  Mapper 接口
 *
 * @author jayud
 * @since 2022-04-25
 */
@Mapper
public interface ReportsMapper extends BaseMapper<Reports> {

    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-25
     * @param: page
     * @param: reports
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.Reports>
     **/
    IPage<Reports> pageList(@Param("page") Page<Reports> page, @Param("reports") Reports reports);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-04-25
     * @param: reports
     * @return: java.util.List<com.zdf.auth.model.po.Reports>
     **/
    List<Reports> list(@Param("reports") Reports reports);


    /**
     * @description 根据id物理删除
     * @author  jayud
     * @date   2022-04-25
     * @param: id
     * @return: int
     **/
    int phyDelById(@Param("id") Long id);

    /**
     * @description 根据id逻辑删除
     * @author  jayud
     * @date   2022-04-25
     * @param: id
     * @param: username
     * @return: int
     **/
    int logicDel(@Param("id") Long id,@Param("username") String username);


    /**
     * @description 查询导出
     * @author  jayud
     * @date   2022-04-25
     * @param: paramMap
     * @return: java.util.List<java.util.LinkedHashMap<java.lang.String,java.lang.Object>>
     **/
    List<LinkedHashMap<String, Object>> queryReportsForExcel(Map<String, Object> paramMap);
}

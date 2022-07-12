package com.zdf.auth.mapper;

import com.zdf.auth.model.bo.QueryCostGenreForm;
import com.zdf.auth.model.po.CostGenre;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zdf.auth.model.vo.CostGenreVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础数据费用类型 Mapper 接口
 *
 * @author jayud
 * @since 2022-04-11
 */
@Mapper
public interface CostGenreMapper extends BaseMapper<CostGenre> {

    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-11
     * @param: page
     * @param: costGenre
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.CostGenre>
     **/
    IPage<CostGenre> pageList(@Param("page") Page<CostGenre> page, @Param("costGenre") CostGenre costGenre);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-04-11
     * @param: costGenre
     * @return: java.util.List<com.zdf.auth.model.po.CostGenre>
     **/
    List<CostGenre> list(@Param("costGenre") CostGenre costGenre);


    /**
     * @description 根据id物理删除
     * @author  jayud
     * @date   2022-04-11
     * @param: id
     * @return: int
     **/
    int phyDelById(@Param("id") Long id);

    /**
     * @description 根据id逻辑删除
     * @author  jayud
     * @date   2022-04-11
     * @param: id
     * @param: username
     * @return: int
     **/
    int logicDel(@Param("id") Long id,@Param("username") String username);


    /**
     * @description 查询导出
     * @author  jayud
     * @date   2022-04-11
     * @param: paramMap
     * @return: java.util.List<java.util.LinkedHashMap<java.lang.String,java.lang.Object>>
     **/
    List<LinkedHashMap<String, Object>> queryCostGenreForExcel(Map<String, Object> paramMap);

    IPage<CostGenreVO> findCostGenreByPage(Page page, @Param("form") QueryCostGenreForm form);
}

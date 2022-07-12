package com.zdf.auth.mapper;

import com.zdf.auth.model.po.BNoRule;
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
 * 编号规则表 Mapper 接口
 *
 * @author jayud
 * @since 2022-03-01
 * @since 2022-03-02
 */
@Mapper
public interface BNoRuleMapper extends BaseMapper<BNoRule> {

    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-03-01
     * @param: page
     * @param: bNoRule
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.zdf.auth.model.po.BNoRule>
     **/
    IPage<BNoRule> pageList(@Param("page") Page<BNoRule> page, @Param("bNoRule") BNoRule bNoRule);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-03-01
     * @param: bNoRule
     * @return: java.util.List<com.zdf.auth.model.po.BNoRule>
     **/
    List<BNoRule> list(@Param("bNoRule") BNoRule bNoRule);


    /**
     * @description 根据id物理删除
     * @author  jayud
     * @date   2022-03-01
     * @param: id
     * @return: int
     **/
    int phyDelById(@Param("id") Long id);

    /**
     * @description 根据id逻辑删除
     * @author  jayud
     * @date   2022-03-01
     * @param: id
     * @param: username
     * @return: int
     **/
    int logicDel(@Param("id") Long id,@Param("username") String username);

    /**
     * 根据code获取单据
     * @param sheetCode
     * @return
     */
    BNoRule getNoRulesBySheetCode(@Param("sheetCode") String sheetCode);

    //获得单号
    void getOrderNo(Map<String,Object> map);
}

package com.ctf.auth.mapper;

import com.ctf.auth.model.po.BBanks;
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
 * 公司银行账户 Mapper 接口
 *
 * @author jayud
 * @since 2022-04-22
 */
@Mapper
public interface BBanksMapper extends BaseMapper<BBanks> {

    /**
     * @description 分页查询
     * @author  jayud
     * @date   2022-04-22
     * @param: page
     * @param: bBanks
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.ctf.auth.model.po.BBanks>
     **/
    IPage<BBanks> pageList(@Param("page") Page<BBanks> page, @Param("bBanks") BBanks bBanks);

    /**
     * @description 列表查询数据
     * @author  jayud
     * @date   2022-04-22
     * @param: bBanks
     * @return: java.util.List<com.ctf.auth.model.po.BBanks>
     **/
    List<BBanks> list(@Param("bBanks") BBanks bBanks);


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
    List<LinkedHashMap<String, Object>> queryBBanksForExcel(Map<String, Object> paramMap);
}

package com.ctf.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.auth.model.po.HsCodeElements;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 海关税号要素明细表 Mapper 接口
 * </p>
 *
 * @author LLJ
 * @since 2021-07-26
 */
@Mapper
public interface HsCodeElementsMapper extends BaseMapper<HsCodeElements> {

}

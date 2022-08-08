package com.ctf.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.generator.entity.DataSourceEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源管理
 *
 * @author H.m
 */
@Mapper
public interface DataSourceMapper extends BaseMapper<DataSourceEntity> {

}

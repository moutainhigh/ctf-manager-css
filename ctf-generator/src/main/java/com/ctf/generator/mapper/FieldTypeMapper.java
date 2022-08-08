package com.ctf.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.generator.entity.FieldTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * 字段类型管理
 *
 * @author H.m
 */
@Mapper
public interface FieldTypeMapper extends BaseMapper<FieldTypeEntity> {

    /**
     * 根据tableId，获取包列表
     */
    Set<String> getPackageListByTableId(Long tableId);

    /**
     * 获取全部字段类型
     */
    Set<String> list();
}

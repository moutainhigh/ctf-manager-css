package com.ctf.common.mybatis.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

/**
 * String 数组类型转换 json
 * <a href="https://www.jianshu.com/p/ab832f3fe81c">https://www.jianshu.com/p/ab832f3fe81c</a>
 *
 * @author H.m
 * @date 2022/08/05 11:25
 */
@Slf4j
@Component
@MappedTypes(value = {String[].class})
@MappedJdbcTypes(value = {JdbcType.OTHER}, includeNullJdbcType = true)
public class StringArrayJsonTypeHandler extends ArrayObjectJsonTypeHandler<String> {
    public StringArrayJsonTypeHandler() {
        super(String[].class);
    }
}

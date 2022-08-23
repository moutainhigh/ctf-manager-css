package com.ctf.css.converter;

import com.ctf.css.pojo.entity.TourScheme;
import com.ctf.css.pojo.vo.ex.TourSchemeVO;
import org.mapstruct.Mapper;

/**
 * @Author zhangyizheng
 * @Date 2022/8/23 10:53
 * @Describe TourSchemeConverter 方案类转换器
 */
@Mapper(componentModel = "spring")
public interface TourSchemeConverter {

    TourSchemeVO entity2VO(TourScheme tourScheme);
}

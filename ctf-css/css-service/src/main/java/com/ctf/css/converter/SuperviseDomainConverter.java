package com.ctf.css.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.SuperviseDomain;
import com.ctf.css.pojo.vo.ex.SuperviseDomainVo;
import org.mapstruct.Mapper;

/**
 * @Author zhangyizheng
 * @Date 2022/8/11 18:22
 * @Describe SuperviseDomainConverter 督导领域转换器
 */
@Mapper(componentModel = "spring")
public interface SuperviseDomainConverter {
    Page<SuperviseDomainVo> pageEntityToVo(Page<SuperviseDomain> page);
}

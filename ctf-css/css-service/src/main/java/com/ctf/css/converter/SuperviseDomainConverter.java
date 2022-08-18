package com.ctf.css.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.common.web.domain.Option;
import com.ctf.css.pojo.entity.SuperviseDomain;
import com.ctf.css.pojo.form.SuperviseDomainForm;
import com.ctf.css.pojo.vo.ex.SuperviseDomainVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @Author zhangyizheng
 * @Date 2022/8/11 18:22
 * @Describe SuperviseDomainConverter 督导领域转换器
 */
@Mapper(componentModel = "spring")
public interface SuperviseDomainConverter {
    Page<SuperviseDomainVo> pageEntityToVo(Page<SuperviseDomain> page);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "superviseDomainName")
    })
    Option domain2Option(SuperviseDomain domain);

    List<Option> domain2Options(List<SuperviseDomain> domain);

}

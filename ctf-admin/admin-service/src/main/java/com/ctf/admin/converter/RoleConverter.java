package com.ctf.admin.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.admin.pojo.entity.SysRole;
import com.ctf.admin.pojo.form.RoleForm;
import com.ctf.admin.pojo.vo.role.RolePageVO;
import com.ctf.common.web.domain.Option;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 角色对象转换器
 *
 * @ClassName RoleConverter
 * @Description
 * @Author H.m
 * @date 2022/8/5 10:30
 * @Version 1.0
 **/
@Mapper(componentModel = "spring")
public interface RoleConverter {

    Page<RolePageVO> entity2Page(Page<SysRole> page);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option role2Option(SysRole role);


    List<Option> roles2Options(List<SysRole> roles);

    SysRole form2Entity(RoleForm roleForm);
}

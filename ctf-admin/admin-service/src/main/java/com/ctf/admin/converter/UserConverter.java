package com.ctf.admin.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.admin.pojo.entity.SysUser;
import com.ctf.admin.pojo.form.UserForm;
import com.ctf.admin.pojo.po.UserFormPO;
import com.ctf.admin.pojo.po.UserPO;
import com.ctf.admin.pojo.vo.user.LoginUserVO;
import com.ctf.admin.pojo.vo.user.UserVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


/**
 * 用户对象转换器
 *
 * @ClassName UserConverter
 * @Description
 * @Author H.m
 * @date 2022/8/5 10:30
 * @Version 1.0
 **/
@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({
            @Mapping(target = "genderLabel", expression = "java(com.ctf.common.base.IBaseEnum.getLabelByValue(po.getGender(), com.ctf.common.enums.GenderEnum.class))")
    })
    UserVO po2Vo(UserPO po);

    Page<UserVO> po2Vo(Page<UserPO> po);

    UserForm po2Form(UserFormPO po);

    UserForm entity2Form(SysUser entity);

    @InheritInverseConfiguration(name = "entity2Form")
    SysUser form2Entity(UserForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    LoginUserVO entity2LoginUser(SysUser entity);
}

package com.ctf.admin.converter;

import com.ctf.admin.pojo.entity.SysMenu;
import com.ctf.admin.pojo.vo.menu.MenuVO;
import org.mapstruct.Mapper;

/**
 * 菜单对象转换器
 *
 * @ClassName MenuConverter
 * @Description
 * @Author H.m
 * @date 2022/8/5 10:30
 * @Version 1.0
 **/
@Mapper(componentModel = "spring")
public interface MenuConverter {

    MenuVO entity2VO(SysMenu entity);

}

package com.ctf.admin.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.admin.pojo.entity.SysOauthClient;
import com.ctf.admin.pojo.vo.client.ClientPageVO;
import org.mapstruct.Mapper;

/**
 * 客户端对象转换器
 *
 * @ClassName ClientConverter
 * @Description
 * @Author H.m
 * @date 2022/8/5 10:30
 * @Version 1.0
 **/
@Mapper(componentModel = "spring")
public interface ClientConverter {

    ClientPageVO entity2PageVO(SysOauthClient entity);

    Page<ClientPageVO> entity2PageVO(Page<SysOauthClient> entityPage);
}

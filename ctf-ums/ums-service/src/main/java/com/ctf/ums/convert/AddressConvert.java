package com.ctf.ums.convert;

import com.ctf.ums.dto.MemberAddressDTO;
import com.ctf.ums.pojo.entity.UmsAddress;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 会员地址对象转换器
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Mapper(componentModel = "spring")
public interface AddressConvert {

    MemberAddressDTO entity2Dto(UmsAddress entity);

    List<MemberAddressDTO> entity2Dto(List<UmsAddress> entities);
}

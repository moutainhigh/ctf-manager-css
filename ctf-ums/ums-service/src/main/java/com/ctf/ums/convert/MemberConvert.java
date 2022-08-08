package com.ctf.ums.convert;

import com.ctf.ums.dto.MemberAuthDTO;
import com.ctf.ums.dto.MemberDTO;
import com.ctf.ums.dto.MemberInfoDTO;
import com.ctf.ums.pojo.entity.UmsMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *  会员对象转换器
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Mapper(componentModel = "spring")
public interface MemberConvert {
    @Mappings({
            @Mapping(target = "memberId", source = "id"),
            @Mapping(target = "username", source = "openid")
    })
    MemberAuthDTO entity2OpenidAuthDTO(UmsMember entity);

    @Mappings({
            @Mapping(target = "memberId", source = "id"),
            @Mapping(target = "username", source = "mobile")
    })
    MemberAuthDTO entity2MobileAuthDTO(UmsMember entity);

    MemberInfoDTO entity2MemberInfoDTO(UmsMember entity);

    UmsMember dto2Entity(MemberDTO memberDTO);
}

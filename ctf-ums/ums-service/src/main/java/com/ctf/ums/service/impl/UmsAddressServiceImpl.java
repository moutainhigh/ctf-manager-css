package com.ctf.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.common.constant.GlobalConstants;
import com.ctf.common.web.util.MemberUtils;
import com.ctf.ums.dto.MemberAddressDTO;
import com.ctf.ums.mapper.UmsAddressMapper;
import com.ctf.ums.pojo.entity.UmsAddress;
import com.ctf.ums.pojo.form.AddressForm;
import com.ctf.ums.service.IUmsAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 会员地址业务实现类
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Service
public class UmsAddressServiceImpl extends ServiceImpl<UmsAddressMapper, UmsAddress> implements IUmsAddressService {

    /**
     * 新增地址
     *
     * @param addressForm
     * @return
     */
    @Override
    @Transactional
    public boolean addAddress(AddressForm addressForm) {
        Long memberId = MemberUtils.getMemberId();

        UmsAddress umsAddress = new UmsAddress();
        BeanUtil.copyProperties(addressForm, umsAddress);
        umsAddress.setMemberId(memberId);
        boolean result = this.save(umsAddress);
        if (result) {
            // 修改其他默认地址为非默认
            if (GlobalConstants.STATUS_YES.equals(addressForm.getDefaulted())) {
                this.update(new LambdaUpdateWrapper<UmsAddress>()
                        .eq(UmsAddress::getMemberId, memberId)
                        .eq(UmsAddress::getDefaulted, 1)
                        .ne(UmsAddress::getId,umsAddress.getId())
                        .set(UmsAddress::getDefaulted, 0)
                );
            }
        }
        return result;
    }

    /**
     * 修改地址
     *
     * @param addressForm
     * @return
     */
    @Override
    public boolean updateAddress(AddressForm addressForm) {
        Long memberId = MemberUtils.getMemberId();

        UmsAddress umsAddress = new UmsAddress();
        BeanUtil.copyProperties(addressForm, umsAddress);

        boolean result = this.updateById(umsAddress);

        if(result){
            // 修改其他默认地址为非默认
            if (GlobalConstants.STATUS_YES.equals(addressForm.getDefaulted())) {
                this.update(new LambdaUpdateWrapper<UmsAddress>()
                        .eq(UmsAddress::getMemberId, memberId)
                        .eq(UmsAddress::getDefaulted, 1)
                        .ne(UmsAddress::getId, umsAddress.getId())
                        .set(UmsAddress::getDefaulted, 0)
                );
            }
        }
        return result;
    }

    /**
     * 获取当前登录会员的地址列表
     *
     * @return
     */
    @Override
    public List<MemberAddressDTO> listCurrentMemberAddresses() {
        Long memberId = MemberUtils.getMemberId();
        List<UmsAddress> umsAddressList = this.list(new LambdaQueryWrapper<UmsAddress>()
                .eq(UmsAddress::getMemberId, memberId)
                .orderByDesc(UmsAddress::getDefaulted) // 默认地址排在首位
        );
        List<MemberAddressDTO> memberAddressList = Optional.ofNullable(umsAddressList).orElse(new ArrayList<>()).stream()
                .map(umsAddress -> {
                    MemberAddressDTO memberAddressDTO = new MemberAddressDTO();
                    BeanUtil.copyProperties(umsAddress, memberAddressDTO);
                    return memberAddressDTO;
                }).collect(Collectors.toList());
        return memberAddressList;
    }
}

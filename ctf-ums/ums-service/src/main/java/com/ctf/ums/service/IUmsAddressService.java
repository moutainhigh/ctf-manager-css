package com.ctf.ums.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.ums.dto.MemberAddressDTO;
import com.ctf.ums.pojo.entity.UmsAddress;
import com.ctf.ums.pojo.form.AddressForm;

import java.util.List;

/**
 * 会员地址业务接口
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public interface IUmsAddressService extends IService<UmsAddress> {

    /**
     * 新增地址
     *
     * @param addressForm
     * @return
     */
    boolean addAddress(AddressForm addressForm);

    /**
     * 修改地址
     *
     * @param addressForm
     * @return
     */
    boolean updateAddress(AddressForm addressForm);

    /**
     * 获取当前登录会员的地址列表
     *
     * @return
     */
    List<MemberAddressDTO> listCurrentMemberAddresses();
}

package com.ctf.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.oms.mapper.OrderSettingMapper;
import com.ctf.oms.pojo.entity.OmsOrderSetting;
import com.ctf.oms.service.IOrderSettingService;
import org.springframework.stereotype.Service;


@Service
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OmsOrderSetting> implements IOrderSettingService {

}

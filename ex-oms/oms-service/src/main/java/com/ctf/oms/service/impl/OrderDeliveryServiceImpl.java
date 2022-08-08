package com.ctf.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.oms.mapper.OrderDeliveryMapper;
import com.ctf.oms.pojo.entity.OmsOrderDelivery;
import com.ctf.oms.service.IOrderDeliveryService;
import org.springframework.stereotype.Service;

@Service("orderDeliveryService")
public class OrderDeliveryServiceImpl extends ServiceImpl<OrderDeliveryMapper, OmsOrderDelivery> implements IOrderDeliveryService {

}

package com.ctf.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.oms.mapper.OrderItemMapper;
import com.ctf.oms.pojo.entity.OmsOrderItem;
import com.ctf.oms.service.IOrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OmsOrderItem> implements IOrderItemService {


}

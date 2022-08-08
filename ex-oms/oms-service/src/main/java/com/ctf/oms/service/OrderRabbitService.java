package com.ctf.oms.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

/**
 * @author H.m
 * @desc Order RabbitMQ 业务类

 * @date 2022/8/5 10:30
 */
public interface OrderRabbitService {

    /**
     * 订单超时释放
     *
     * @param orderSn
     * @param message
     * @param channel
     */
    void releaseOrder(String orderSn, Message message, Channel channel);
}

package com.ctf.oms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.oms.pojo.entity.OmsOrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单商品明细表
 *
 * @author H.m

 * @date 2022/8/5 10:30
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OmsOrderItem> {

}

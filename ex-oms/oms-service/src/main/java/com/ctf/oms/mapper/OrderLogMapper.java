package com.ctf.oms.mapper;

import com.ctf.oms.pojo.entity.OmsOrderLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录
 *
 * @author H.m

 * @date 2022/8/5 10:30
 */
@Mapper
public interface OrderLogMapper extends BaseMapper<OmsOrderLog> {

}

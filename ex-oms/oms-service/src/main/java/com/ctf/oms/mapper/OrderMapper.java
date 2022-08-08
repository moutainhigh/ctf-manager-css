package com.ctf.oms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.oms.pojo.entity.OmsOrder;
import com.ctf.oms.pojo.query.OrderPageQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 订单详情表
 *
 * @author H.m

 * @date 2022/8/5 10:30
 */
@Mapper
public interface OrderMapper extends BaseMapper<OmsOrder> {

    /**
     * 订单分页列表
     *
     * @param page
     * @param queryParams
     * @return
     */
    List<OmsOrder> listOrderPages(Page<OmsOrder> page, OrderPageQuery queryParams);
}

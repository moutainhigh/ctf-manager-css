package com.ctf.sms.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.sms.pojo.entity.SmsCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.sms.pojo.query.CouponPageQuery;
import com.ctf.sms.pojo.vo.CouponPageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SmsCouponMapper extends BaseMapper<SmsCoupon> {

    List<SmsCoupon> listCouponPages(Page<CouponPageVO> page, CouponPageQuery queryParams);
}





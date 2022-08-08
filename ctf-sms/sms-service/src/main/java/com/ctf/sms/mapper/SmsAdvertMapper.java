package com.ctf.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.sms.pojo.entity.SmsAdvert;
import com.ctf.sms.pojo.query.AdvertPageQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmsAdvertMapper extends BaseMapper<SmsAdvert> {

    /**
     * 广告分页列表
     *
     * @param page
     * @param queryParams
     * @return
     */
    List<SmsAdvert> listAdvertsPage(Page<SmsAdvert> page, AdvertPageQuery queryParams);
}

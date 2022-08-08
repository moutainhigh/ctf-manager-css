package com.ctf.sms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.sms.pojo.entity.SmsAdvert;
import com.ctf.sms.mapper.SmsAdvertMapper;
import com.ctf.sms.pojo.query.AdvertPageQuery;
import com.ctf.sms.service.SmsAdvertService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告业务实现类
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Service
public class SmsAdvertServiceImpl extends ServiceImpl<SmsAdvertMapper, SmsAdvert> implements SmsAdvertService {

    /**
     * 广告分页列表
     *
     * @param queryParams
     * @return
     */
    @Override
    public Page<SmsAdvert> listAdvertsPage(AdvertPageQuery queryParams) {
        Page<SmsAdvert> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        List<SmsAdvert> list = this.baseMapper.listAdvertsPage(page, queryParams);
        page.setRecords(list);
        return page;
    }
}

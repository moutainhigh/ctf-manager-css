package com.ctf.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.sms.pojo.entity.SmsAdvert;
import com.ctf.sms.pojo.query.AdvertPageQuery;

public interface SmsAdvertService extends IService<SmsAdvert> {

    /**
     * 广告分页列表
     *
     * @param queryParams
     * @return
     */
    Page<SmsAdvert> listAdvertsPage(AdvertPageQuery queryParams);
}

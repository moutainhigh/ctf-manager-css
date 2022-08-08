package com.ctf.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.admin.pojo.entity.SysOauthClient;
import com.ctf.admin.pojo.query.ClientPageQuery;
import com.ctf.admin.pojo.vo.client.ClientPageVO;

/**
 * OAuth2客户端接口
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
public interface SysOauthClientService extends IService<SysOauthClient> {

    /**
     * 客户端分页列表
     *
     * @param queryParams
     * @return
     */
    IPage<ClientPageVO> listClientPages(ClientPageQuery queryParams);


    void cleanCache();
}

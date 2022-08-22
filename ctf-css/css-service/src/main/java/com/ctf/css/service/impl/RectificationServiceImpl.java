package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.pojo.entity.Rectification;
import com.ctf.css.pojo.query.RestultPageQuery;
import com.ctf.css.pojo.vo.ex.RectificationVO;
import com.ctf.css.service.RectificationService;
import com.ctf.css.mapper.RectificationMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangyizheng
* @description 针对表【store_rectification(门店整改表)】的数据库操作Service实现
* @createDate 2022-08-09 20:47:20
*/
@Service
public class RectificationServiceImpl extends ServiceImpl<RectificationMapper, Rectification>
    implements RectificationService{

    @Override
    public Page<RectificationVO> pageRectification(RestultPageQuery queryParams) {
        Page<RectificationVO> page = new Page<>();
        page.setCurrent(queryParams.getPageNum());
        page.setSize(queryParams.getPageSize());
        return this.baseMapper.pageRectificationList(page, queryParams);
    }
}





package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.pojo.entity.SelfInspection;
import com.ctf.css.pojo.query.SelfInspectionPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionVO;
import com.ctf.css.service.SelfInspectionService;
import com.ctf.css.mapper.SelfInspectionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author zhangyizheng
* @description 针对表【store_self_inspection(门店自检表)】的数据库操作Service实现
* @createDate 2022-08-09 20:47:20
*/
@Service
@RequiredArgsConstructor
public class SelfInspectionServiceImpl extends ServiceImpl<SelfInspectionMapper, SelfInspection>
    implements SelfInspectionService{


    @Override
    public Page<InspectionVO> pageInspection(SelfInspectionPageQuery queryParams) {
        Page<InspectionVO> page = new Page<>();
        page.setCurrent(queryParams.getPageNum());
        page.setSize(queryParams.getPageSize());
        page = this.baseMapper.getInspectionPage(page, queryParams);
        return page;
    }
}





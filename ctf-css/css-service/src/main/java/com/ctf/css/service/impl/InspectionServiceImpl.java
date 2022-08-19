package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.pojo.entity.Inspection;
import com.ctf.css.pojo.query.InspectionPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionVO;
import com.ctf.css.service.InspectionService;
import com.ctf.css.mapper.InspectionMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangyizheng
* @description 针对表【store_inspection(巡检表)】的数据库操作Service实现
* @createDate 2022-08-09 20:47:20
*/
@Service
public class InspectionServiceImpl extends ServiceImpl<InspectionMapper, Inspection>
    implements InspectionService{

    @Override
    public Page<InspectionVO> pageInspection(InspectionPageQuery queryParams) {
        Page<InspectionVO> page = new Page<>();
        page.setCurrent(queryParams.getPageNum());
        page.setSize(queryParams.getPageSize());
        return this.baseMapper.getListInspectionsPage(page, queryParams);
    }
}





package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.pojo.entity.InspectionResult;
import com.ctf.css.pojo.query.RestultPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionResultVO;
import com.ctf.css.service.InspectionResultService;
import com.ctf.css.mapper.InspectionResultMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangyizheng
* @description 针对表【store_inspection_result(巡检结果表)】的数据库操作Service实现
* @createDate 2022-08-21 11:20:58
*/
@Service
public class InspectionResultServiceImpl extends ServiceImpl<InspectionResultMapper, InspectionResult>
    implements InspectionResultService{

    @Override
    public Page<InspectionResultVO> pageInspectionResult(RestultPageQuery queryParams) {
        Page<InspectionResultVO> page = new Page<>();
        page.setCurrent(queryParams.getPageNum());
        page.setSize(queryParams.getPageSize());
        return this.baseMapper.pageInspectionResult(page, queryParams);
    }
}





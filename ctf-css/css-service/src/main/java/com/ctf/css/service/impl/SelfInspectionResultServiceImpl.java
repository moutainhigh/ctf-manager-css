package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.pojo.entity.SelfInspectionResult;
import com.ctf.css.pojo.query.RestultPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionResultVO;
import com.ctf.css.service.SelfInspectionResultService;
import com.ctf.css.mapper.SelfInspectionResultMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangyizheng
* @description 针对表【store_self_inspection_result(自检结果表)】的数据库操作Service实现
* @createDate 2022-08-21 11:20:58
*/
@Service
public class SelfInspectionResultServiceImpl extends ServiceImpl<SelfInspectionResultMapper, SelfInspectionResult>
    implements SelfInspectionResultService{

    @Override
    public Page<InspectionResultVO> pageSelfResult(RestultPageQuery queryParams) {
        Page<InspectionResultVO> page = new Page<>();
        page.setCurrent(queryParams.getPageNum());
        page.setSize(queryParams.getPageSize());
        return this.baseMapper.pageSelfResult(page, queryParams);
    }
}





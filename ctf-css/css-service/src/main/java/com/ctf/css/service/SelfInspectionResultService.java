package com.ctf.css.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.SelfInspectionResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.css.pojo.query.RestultPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionResultVO;

/**
* @author zhangyizheng
* @description 针对表【store_self_inspection_result(自检结果表)】的数据库操作Service
* @createDate 2022-08-21 11:20:58
*/
public interface SelfInspectionResultService extends IService<SelfInspectionResult> {

    /**
     * 自检结果-分页查询
     * @param queryParams 查询条件
     * @return
     */
    Page<InspectionResultVO> pageSelfResult(RestultPageQuery queryParams);
}

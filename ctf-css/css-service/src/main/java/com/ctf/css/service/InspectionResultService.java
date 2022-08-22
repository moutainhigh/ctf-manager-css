package com.ctf.css.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.css.pojo.entity.InspectionResult;
import com.ctf.css.pojo.query.RestultPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionResultVO;

/**
* @author zhangyizheng
* @description 针对表【store_inspection_result(巡检结果表)】的数据库操作Service
* @createDate 2022-08-21 11:20:58
*/
public interface InspectionResultService extends IService<InspectionResult> {

    /**
     * 巡检结果-分页查询
     * @param queryParams 分页条件
     * @return
     */
    Page<InspectionResultVO> pageInspectionResult(RestultPageQuery queryParams);

    /**
     * 巡检结果-是否整改
     * @param id 巡检结果ID
     * @return
     */
    boolean isRectification(Long id);
}

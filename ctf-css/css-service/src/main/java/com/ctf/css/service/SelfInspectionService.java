package com.ctf.css.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.SelfInspection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.css.pojo.query.SelfInspectionPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionVO;

/**
* @author zhangyizheng
* @description 针对表【store_self_inspection(门店自检表)】的数据库操作Service
* @createDate 2022-08-09 20:47:20
*/
public interface SelfInspectionService extends IService<SelfInspection> {

    /**
     * 自检-分页列表
     * @param queryParams 条件查询
     * @return
     */
    Page<InspectionVO> pageInspection(SelfInspectionPageQuery queryParams);
}

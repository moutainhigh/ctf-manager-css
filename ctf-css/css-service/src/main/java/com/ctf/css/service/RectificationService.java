package com.ctf.css.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.Rectification;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.css.pojo.query.RestultPageQuery;
import com.ctf.css.pojo.vo.ex.RectificationVO;

/**
* @author zhangyizheng
* @description 针对表【store_rectification(门店整改表)】的数据库操作Service
* @createDate 2022-08-09 20:47:20
*/
public interface RectificationService extends IService<Rectification> {

    /**
     * 整改结果-分页查询
     * @param queryParams 分页条件
     * @return
     */
    Page<RectificationVO> pageRectification(RestultPageQuery queryParams);
}

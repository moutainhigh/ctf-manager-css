package com.ctf.css.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.TourScheme;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.css.pojo.query.TourSchemePageQuery;
import com.ctf.css.pojo.vo.ex.TourSchemeVO;

/**
* @author zhangyizheng
* @description 针对表【store_tour_scheme(巡检方案表)】的数据库操作Service
* @createDate 2022-08-09 20:47:20
*/
public interface TourSchemeService extends IService<TourScheme> {

    /**
     * 方案管理-分页列表
     * @param queryParams 分页条件
     * @return
     */
    Page<TourSchemeVO> pageTourScheme(TourSchemePageQuery queryParams);

    /**
     * 方案管理-预览
     * @param id
     * @return
     */
    TourSchemeVO getOneById(Long id);

    /**
     * 方案管理-删除
     * @param id 方案id
     * @return
     */
    boolean deleteOne(Long id);
}

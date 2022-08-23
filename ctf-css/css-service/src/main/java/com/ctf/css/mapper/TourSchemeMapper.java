package com.ctf.css.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.TourScheme;
import com.ctf.css.pojo.query.TourSchemePageQuery;
import com.ctf.css.pojo.vo.ex.TourSchemeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author zhangyizheng
* @description 针对表【store_tour_scheme(巡检方案表)】的数据库操作Mapper
* @createDate 2022-08-09 20:47:20
* @Entity com.ctf.css.pojo.entity.TourScheme
*/
@Mapper
public interface TourSchemeMapper extends BaseMapper<TourScheme> {

    /**
     * 方案分页条件查询
     * @param page
     * @param queryParams
     * @return
     */
    Page<TourSchemeVO> pageList(IPage<TourSchemeVO> page, @Param("query")TourSchemePageQuery queryParams);
}





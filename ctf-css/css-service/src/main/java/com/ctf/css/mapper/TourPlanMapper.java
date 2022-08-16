package com.ctf.css.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.TourPlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.css.pojo.query.TourPlanPageQuery;
import com.ctf.css.pojo.vo.ex.TourPlanVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

/**
* @author zhangyizheng
* @description 针对表【store_tour_plan(巡店计划表)】的数据库操作Mapper
* @createDate 2022-08-09 20:47:20
* @Entity com.ctf.css.pojo.entity.TourPlan
*/
@Mapper
public interface TourPlanMapper extends BaseMapper<TourPlan> {

    /**
     * 巡店计划-门店信息分页查询
     * @param page 页码与当前页
     * @param query 查询条件
     * @return
     */
    Page<TourPlanVo> selectPageByQuery(IPage<TourPlanVo> page,@Param("query") TourPlanPageQuery query);
}





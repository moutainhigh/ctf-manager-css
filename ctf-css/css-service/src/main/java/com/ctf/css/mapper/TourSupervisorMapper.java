package com.ctf.css.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.TourSupervisor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.css.pojo.query.TourSupervisorPageQuery;
import com.ctf.css.pojo.vo.ex.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

/**
* @author zhangyizheng
* @description 针对表【store_tour_supervisor(巡店督导员表)】的数据库操作Mapper
* @createDate 2022-08-09 20:47:20
* @Entity com.ctf.css.pojo.entity.TourSupervisor
*/
@Mapper
public interface TourSupervisorMapper extends BaseMapper<TourSupervisor> {

    Page<UserVO> getTourSupervisor(Page page, @Param("query") TourSupervisorPageQuery query);
}





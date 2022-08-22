package com.ctf.css.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.Rectification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.css.pojo.query.RestultPageQuery;
import com.ctf.css.pojo.vo.ex.RectificationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author zhangyizheng
* @description 针对表【store_rectification(门店整改表)】的数据库操作Mapper
* @createDate 2022-08-09 20:47:20
* @Entity com.ctf.css.pojo.entity.Rectification
*/
@Mapper
public interface RectificationMapper extends BaseMapper<Rectification> {

    Page<RectificationVO> pageRectificationList(IPage<RectificationVO> page,@Param("query") RestultPageQuery queryParams);
}





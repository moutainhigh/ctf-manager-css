package com.ctf.css.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.Inspection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.css.pojo.query.InspectionPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author zhangyizheng
* @description 针对表【store_inspection(巡检表)】的数据库操作Mapper
* @createDate 2022-08-09 20:47:20
* @Entity com.ctf.css.pojo.entity.Inspection
*/
@Mapper
public interface InspectionMapper extends BaseMapper<Inspection> {

    /**
     * 巡检分页查询
     * @param page
     * @param queryParams 条件
     * @return
     */
    Page<InspectionVO> getListInspectionsPage(IPage<InspectionVO> page,@Param("query") InspectionPageQuery queryParams);
}





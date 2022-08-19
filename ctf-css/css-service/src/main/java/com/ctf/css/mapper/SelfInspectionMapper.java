package com.ctf.css.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.SelfInspection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.css.pojo.query.SelfInspectionPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
* @author zhangyizheng
* @description 针对表【store_self_inspection(门店自检表)】的数据库操作Mapper
* @createDate 2022-08-09 20:47:20
* @Entity com.ctf.css.pojo.entity.SelfInspection
*/
@Mapper
public interface SelfInspectionMapper extends BaseMapper<SelfInspection> {

    /**
     * 自检-条件分页查询
     * @param page 分页对象
     * @param queryParams 分页条件
     * @return
     */
    Page<InspectionVO> getInspectionPage(IPage<InspectionVO> page,@Param("query") SelfInspectionPageQuery queryParams);
}





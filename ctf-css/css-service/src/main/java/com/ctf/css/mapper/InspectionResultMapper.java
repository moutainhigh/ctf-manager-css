package com.ctf.css.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.InspectionResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.css.pojo.query.RestultPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author zhangyizheng
* @description 针对表【store_inspection_result(巡检结果表)】的数据库操作Mapper
* @createDate 2022-08-21 11:20:58
* @Entity com.ctf.css.pojo.entity.InspectionResult
*/
@Mapper
public interface InspectionResultMapper extends BaseMapper<InspectionResult> {

    Page<InspectionResultVO> pageInspectionResult(IPage<InspectionResultVO> page,@Param("query") RestultPageQuery queryParams);
}





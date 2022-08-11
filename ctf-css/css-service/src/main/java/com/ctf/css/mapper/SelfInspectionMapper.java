package com.ctf.css.mapper;

import com.ctf.css.pojo.entity.SelfInspection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zhangyizheng
* @description 针对表【store_self_inspection(门店自检表)】的数据库操作Mapper
* @createDate 2022-08-09 20:47:20
* @Entity com.ctf.css.pojo.entity.SelfInspection
*/
@Mapper
public interface SelfInspectionMapper extends BaseMapper<SelfInspection> {

}





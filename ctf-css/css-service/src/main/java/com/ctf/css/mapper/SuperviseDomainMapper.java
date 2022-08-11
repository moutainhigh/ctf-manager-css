package com.ctf.css.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.SuperviseDomain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.css.pojo.query.SupervisorDomainPageQuery;
import com.ctf.css.pojo.vo.ex.SuperviseDomainVo;
import com.ctf.css.pojo.vo.ex.UserVO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zhangyizheng
* @description 针对表【store_supervise_domain(督导领域表)】的数据库操作Mapper
* @createDate 2022-08-09 20:47:20
* @Entity com.ctf.css.pojo.entity.SuperviseDomain
*/
@Mapper
public interface SuperviseDomainMapper extends BaseMapper<SuperviseDomain> {

}





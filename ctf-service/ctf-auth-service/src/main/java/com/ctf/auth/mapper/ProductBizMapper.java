package com.ctf.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.auth.model.bo.QueryProductBizForm;
import com.ctf.auth.model.po.ProductBiz;
import com.ctf.auth.model.vo.ProductBizVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ProductBizMapper extends BaseMapper<ProductBiz> {

    IPage<ProductBizVO> findProductBizByPage(Page page, @Param("form") QueryProductBizForm form);
}

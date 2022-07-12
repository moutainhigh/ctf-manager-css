package com.zdf.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdf.auth.model.bo.QueryProductBizForm;
import com.zdf.auth.model.po.ProductBiz;
import com.zdf.auth.model.vo.ProductBizVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ProductBizMapper extends BaseMapper<ProductBiz> {

    IPage<ProductBizVO> findProductBizByPage(Page page, @Param("form") QueryProductBizForm form);
}

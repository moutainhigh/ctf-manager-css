package com.ctf.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctf.pms.pojo.dto.SkuInfoDTO;
import com.ctf.pms.pojo.entity.PmsSku;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存单元(SKU)持久层
 *
 * @author H.m
 * @date 2022/2/6
 */
@Mapper
public interface PmsSkuMapper extends BaseMapper<PmsSku> {

    /**
     * 获取商品库存单元信息
     *
     * @param skuId
     * @return
     */
    SkuInfoDTO getSkuInfo(Long skuId);
}

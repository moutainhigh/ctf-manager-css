package com.ctf.oms.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ctf.common.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单明细表
 *
 * @author H.m

 * @date 2022/8/5 10:30
 */
@Data
@Accessors(chain = true)
public class OmsOrderItem extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 规格ID
     */
    private Long skuId;

    /**
     * SKU编号
     */
    private String skuSn;

    /**
     * 规格名称
     */
    private String skuName;

    /**
     * 商品sku图片
     */
    private String picUrl;

    /**
     * 商品单价(单位：分)
     */
    private Long price;

    /**
     * 商品数量
     */
    private Integer count;

    /**
     * 商品总金额(单位：分)
     */
    private Long totalAmount;

    /**
     * 逻辑删除(0:正常；1:已删除)
     */
    private Integer deleted;

}

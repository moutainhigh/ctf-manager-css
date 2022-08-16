package com.ctf.css.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.ctf.common.base.BaseEntity;
import lombok.Data;

/**
 * 门店信息表
 * @TableName store_info
 */
@TableName(value ="store_info")
@Data
public class StoreInfo extends BaseEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 店铺编码
     */
    private String storeCode;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 品牌名称
     */
    private String bandName;

    /**
     * 店铺负责人
     */
    private String storeManager;

    /**
     * 上级负责人
     */
    private String storeSuperManager;

    /**
     * 电话号码
     */
    private String storeMobile;

    /**
     * 行政区域
     */
    private String storeArea;

    /**
     * 经营区域(area_info)
     */
    private String areaName;

    /**
     * 经营模式(dict_item)
     */
    private String managerPattern;

    /**
     * 店铺类型(dict_item)
     */
    private String storeType;

    /**
     * 店铺面积
     */
    private String storeDimension;

    /**
     * 所属商圈(dict_item)
     */
    private String theirBus;

    /**
     * 主营产品,多个用逗号分开
     */
    private String mainShop;

    /**
     * 装修风格(dict_item)
     */
    private String decorationStyle;

    /**
     * 装修花费
     */
    private String decorationCost;

    /**
     * 装修地址
     */
    private String decorationAdr;

    /**
     * 店铺地址,多个用逗号分开
     */
    private String storePic;

    /**
     * 经度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建人
     */
    private Long createdBy;


    /**
     * 更新人
     */
    private Long updatedBy;


}
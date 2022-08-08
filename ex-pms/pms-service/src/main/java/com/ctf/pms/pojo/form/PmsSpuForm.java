package com.ctf.pms.pojo.form;

import com.ctf.pms.pojo.entity.PmsSku;
import lombok.Data;

import java.util.List;

/**
 * 商品SPU表单对象
 *
 * @author H.m
 * @date  2022/7/2
 */
@Data
public class PmsSpuForm {

    private Long id;
    private String name;
    private Long categoryId;
    private Long brandId;
    private Long originPrice;
    private Long price;
    private String picUrl;
    private String[] subPicUrls;
    private String description;
    private String detail;

    private List<PmsSpuAttributeForm> attrList;

    private List<PmsSpuAttributeForm> specList;

    private List<PmsSku> skuList;
}

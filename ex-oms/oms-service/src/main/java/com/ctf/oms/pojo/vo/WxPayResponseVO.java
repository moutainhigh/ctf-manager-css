package com.ctf.oms.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author H.m
 * @date 2022/08/05 11:25
 */
@Data
@Accessors(chain = true)
public class WxPayResponseVO {
    private String code;

    private String message;
}

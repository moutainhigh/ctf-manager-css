package com.ctf.pms.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author H.m
 * @date 2022/08/05 11:25
 */
@Data
public class ProductHistoryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String picUrl;
}

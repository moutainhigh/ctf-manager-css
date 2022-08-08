package com.ctf.lab.seata.pojo.form;

import lombok.Data;

/**
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Data
public class SeataForm {


    /**
     * 是否开启事务
     */
    private boolean openTx;

    /**
     * 订单异常
     */
    private boolean orderEx;


}

package com.ctf.lab.seata.service;

import com.ctf.lab.seata.pojo.form.SeataForm;
import com.ctf.lab.seata.pojo.vo.SeataDataVO;

/**
 * @author H.m
 * @date 2022/8/5 10:30
 */
public interface ISeataService {

    /**
     * 模拟订单支付
     *
     * @return
     */
    boolean payOrder(SeataForm seataForm);

    /**
     * 模拟订单支付(分布式事务)
     *
     * @param seataForm
     * @return
     */
    boolean payOrderWithGlobalTx(SeataForm seataForm);

    /**
     * 获取模拟数据
     *
     * @return
     */
    SeataDataVO getData();

    /**
     * 重置模拟数据
     *
     * @return
     */
    boolean resetData();
}

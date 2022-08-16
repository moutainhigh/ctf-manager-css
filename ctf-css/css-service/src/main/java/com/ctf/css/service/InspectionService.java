package com.ctf.css.service;

import com.ctf.css.pojo.entity.Inspection;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhangyizheng
* @description 针对表【store_inspection(巡检表)】的数据库操作Service
* @createDate 2022-08-09 20:47:20
*/
public interface InspectionService extends IService<Inspection> {
    /**
     * 未启动
     */
    public static final String UNSTART = "0";
    /**
     * 进行中
     */
    public static final String UNDER_WAY = "1";
    /**
     * 已完成
     */
    public static final String COMPLETED = "2";

}

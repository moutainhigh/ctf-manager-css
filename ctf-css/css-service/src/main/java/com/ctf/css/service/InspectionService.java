package com.ctf.css.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.dto.InspectionDTO;
import com.ctf.css.pojo.entity.Inspection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.css.pojo.query.InspectionPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionVO;

import java.util.List;

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

    /**
     * 巡检类型：2 自检
     */
    public static final int TYPE_SELF_INSPECTION = 2;

    /**
     * 巡检分页查询接口
     * @param queryParams 分页条件
     * @return
     */
    Page<InspectionVO> pageInspection(InspectionPageQuery queryParams);

    /**
     * 将未启动的任务启动
     * @param dto
     * @return
     */
    boolean startInspection(InspectionDTO dto);

    /**
     * 将未启动的任务删除
     * @param dto
     * @return
     */
    boolean deleteInspection(InspectionDTO dto);
}

package com.ctf.css.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.css.pojo.entity.TourPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctf.css.pojo.form.InspectionPlanForm;
import com.ctf.css.pojo.query.TourPlanPageQuery;
import com.ctf.css.pojo.vo.ex.TourPlanVo;

/**
* @author zhangyizheng
* @description 针对表【store_tour_plan(巡店计划表)】的数据库操作Service
* @createDate 2022-08-09 20:47:20
*/
public interface TourPlanService extends IService<TourPlan> {

    /**
     * 未计划
     */
    public static final String UNPLANNED = "0";
    /**
     * 已计划未分配
     */
    public static final String PLANNED = "1";
    /**
     * 已分配
     */
    public static final String ALLOCATED = "2";
    /**
     * 任务已下发
     */
    public static final String TASKS_ARE_ISSUED = "3";

    /**
     * 巡检类型：0 独立
     */
    public static final int TYPE_INDEPENDENT = 0;
    /**
     * 巡检类型：1 联合
     */
    public static final int TYPE_UNITE = 1;

    /**
     * 巡店计划-门店信息分页查询
     * @param queryParams 分页条件
     * @return
     */
    Page<TourPlanVo> pagePlan(TourPlanPageQuery queryParams);

    /**
     * 门店批量-加入巡检
     * @param staffCodes 门店编码集
     * @return
     */
    boolean saveInspectionPlan(String staffCodes);

    /**
     * 选择巡检方式-批量
     * @param ids 巡检ID集
     * @param type 巡检类型 0：独立 1：联合
     * @return
     */
    boolean selectType(String ids, Integer type);

    /**
     * 选择督导员-批量
     * @param form
     * @return
     */
    boolean selectSupervisorAndType(InspectionPlanForm form);

    /**
     * 任务下发-批量
     * @param ids
     * @return
     */
    boolean taskIssued(String ids);
}

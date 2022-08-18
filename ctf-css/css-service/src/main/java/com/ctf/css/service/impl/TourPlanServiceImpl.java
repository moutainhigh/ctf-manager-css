package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.converter.InspectionConverter;
import com.ctf.css.mapper.TourPlanMapper;
import com.ctf.css.pojo.entity.Inspection;
import com.ctf.css.pojo.entity.StoreInfo;
import com.ctf.css.pojo.entity.TourPlan;
import com.ctf.css.pojo.form.InspectionPlanForm;
import com.ctf.css.pojo.query.TourPlanPageQuery;
import com.ctf.css.pojo.vo.ex.TourPlanVo;
import com.ctf.css.service.InspectionService;
import com.ctf.css.service.StoreInfoService;
import com.ctf.css.service.TourPlanService;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author zhangyizheng
* @description 针对表【store_tour_plan(巡店计划表)】的数据库操作Service实现
* @createDate 2022-08-09 20:47:20
*/
@Service
@RequiredArgsConstructor
public class TourPlanServiceImpl extends ServiceImpl<TourPlanMapper, TourPlan>
    implements TourPlanService{

    private final StoreInfoService storeInfoService;
    private final InspectionService inspectionService;
    private final InspectionConverter inspectionConverter;

    @Override
    public Page<TourPlanVo> pagePlan(TourPlanPageQuery queryParams) {
        Page<TourPlanVo> page = new Page<>();
        page.setCurrent(queryParams.getPageNum());
        page.setSize(queryParams.getPageSize());
        page = this.baseMapper.selectPageByQuery(page,queryParams);
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveInspectionPlan(String staffCodes) {
        if (StrUtil.isNotBlank(staffCodes)) {
            String[] split = staffCodes.split(",|，");
            List<String> collect = Arrays.stream(split).collect(Collectors.toList());
            // 2022/8/15 获取所有门店ID
            List<StoreInfo> storeInfos = storeInfoService.getBaseMapper().selectList(new LambdaQueryWrapper<StoreInfo>().in(StoreInfo::getStoreCode, collect)
                    .select(StoreInfo::getId));
            // 2022/8/15 将门店批量加入巡检,巡检状态变为0（未计划）
            storeInfos.forEach(item -> {
                TourPlan tourPlan = new TourPlan();
                tourPlan.setStoreId(item.getId());
                tourPlan.setStatus(TourPlanService.UNPLANNED);
                this.baseMapper.insert(tourPlan);
            });
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean selectType(String ids, Integer type) {
        Optional.ofNullable(ids).orElseThrow(() -> new IllegalArgumentException("ID参数不可为空"));
        Optional.ofNullable(type).orElseThrow(() -> new IllegalArgumentException("类型参数不可为空"));
        if (StrUtil.isNotBlank(ids)) {
            //获取批量ID
            String[] split = ids.split(",|，");
            List<Long> collect = Arrays.stream(split).map(Long::parseLong).collect(Collectors.toList());
            //通过ID查询巡检记录，修改状态为1（已计划未分配），类型为0：独立，1：联合
            List<TourPlan> tourPlans = this.baseMapper.selectBatchIds(collect);
            tourPlans.forEach(item -> {
                item.setStatus(TourPlanService.PLANNED);
                item.setInspectionType(type);
                updateById(item);
            });
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean selectSupervisorAndType(InspectionPlanForm form) {
        Optional.ofNullable(form.getId()).orElseThrow(() -> new IllegalArgumentException("未选择需分配的计划对象"));
        Optional.ofNullable(form.getInspectionType()).orElseThrow(() -> new IllegalArgumentException("未选择巡检类型"));
        Optional.ofNullable(form.getSupervisors()).orElseThrow(() -> new IllegalArgumentException("未填写督导人员"));
        Optional.ofNullable(form.getInspectionTime()).orElseThrow(() -> new IllegalArgumentException("未填写巡检时间"));
        TourPlan tourPlan = this.baseMapper.selectById(form.getId());
        //通过ID查询巡检记录，修改时间与状态为2（已分配），添加督导人员和领域和时间
        tourPlan.setStatus(TourPlanService.ALLOCATED);
        tourPlan.setInspectionTime(form.getInspectionTime());
        //  2022/8/16 分联合巡检与独立巡检 独立巡检只能一个督导，联合巡检一个对多个督导
        if (TourPlanService.TYPE_INDEPENDENT==form.getInspectionType()) {
            if (1<form.getSupervisors().size()) {
                throw new IllegalArgumentException("数据异常");
            }
            //独立巡检，只需修改状态与添加人员与领域
            form.getSupervisors().forEach(item -> {
                //  2022/8/16 注意联合会有多个督导 联合巡检在此之后，生成多条已分配记录
                tourPlan.setSuperviseId(item.getId());
                tourPlan.setSuperviseDomainId(item.getSuperviseDomainId());
                this.updateById(tourPlan);
            });
        }else {
            //删除最开始的一条,复制添加多条联合巡检数据
            this.baseMapper.deleteById(form.getId());
            tourPlan.setId(null);
            //联合巡检，会生成多条巡检计划记录，每一人员一条
            form.getSupervisors().forEach(item -> {
                //  2022/8/16 注意联合会有多个督导 联合巡检在此之后，生成多条已分配记录
                tourPlan.setSuperviseId(item.getId());
                tourPlan.setSuperviseDomainId(item.getSuperviseDomainId());
                this.baseMapper.insert(tourPlan);
            });
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean taskIssued(String ids) {
        if (StrUtil.isNotBlank(ids)) {
            //获取批量ID
            String[] split = ids.split(",|，");
            List<Long> collect = Arrays.stream(split).map(Long::parseLong).collect(Collectors.toList());
            //通过ID，修改状态为3（任务已下发）
            LambdaUpdateWrapper<TourPlan> in = new LambdaUpdateWrapper<TourPlan>()
                    .set(TourPlan::getStatus, TourPlanService.TASKS_ARE_ISSUED)
                    .in(TourPlan::getId, collect);
            if (this.update(in)) {
                // 2022/8/16 添加下发任务到任务下发界面
                List<TourPlan> tourPlans = this.baseMapper.selectBatchIds(collect);
                List<Inspection> inspections = inspectionConverter.planToInspection(tourPlans);
                inspections.forEach(item -> {
                    //将id设为null，任务状态改为未计划0
                    item.setId(null);
                    item.setStatus(InspectionService.UNSTART);
                    inspectionService.getBaseMapper().insert(item);
                });
            }
            return true;
        }
        return false;
    }
}





package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.mapper.StoreInfoMapper;
import com.ctf.css.pojo.entity.StoreInfo;
import com.ctf.css.pojo.entity.TourPlan;
import com.ctf.css.pojo.form.InspectionPlanForm;
import com.ctf.css.pojo.query.TourPlanPageQuery;
import com.ctf.css.pojo.vo.ex.TourPlanVo;
import com.ctf.css.service.TourPlanService;
import com.ctf.css.mapper.TourPlanMapper;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Arg;
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

    private final TourPlanMapper tourPlanMapper;
    private final StoreInfoMapper storeInfoMapper;

    @Override
    public Page<TourPlanVo> pagePlan(TourPlanPageQuery queryParams) {
        Page<TourPlanVo> page = new Page<>();
        page.setCurrent(queryParams.getPageNum());
        page.setSize(queryParams.getPageSize());
        page = tourPlanMapper.selectPageByQuery(page,queryParams);
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveInspectionPlan(String staffCodes) {
        if (StrUtil.isNotBlank(staffCodes)) {
            String[] split = staffCodes.split(",|，");
            List<String> collect = Arrays.stream(split).collect(Collectors.toList());
            // 2022/8/15 获取所有门店ID
            List<StoreInfo> storeInfos = storeInfoMapper.selectList(new LambdaQueryWrapper<StoreInfo>().in(StoreInfo::getStoreCode, collect)
                    .select(StoreInfo::getId));
            // 2022/8/15 将门店批量加入巡检,巡检状态变为0（未计划）
            storeInfos.forEach(item -> {
                TourPlan tourPlan = new TourPlan();
                tourPlan.setStoreId(item.getId());
                tourPlan.setStatus(TourPlanService.UNPLANNED);
                tourPlanMapper.insert(tourPlan);
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
            List<TourPlan> tourPlans = tourPlanMapper.selectBatchIds(collect);
            tourPlans.forEach(item -> {
                item.setStatus(TourPlanService.PLANNED);
                item.setInspectionType(type);
                tourPlanMapper.updateById(item);
            });
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean selectSupervisorAndType(InspectionPlanForm form) {
        Optional.ofNullable(form.getIds()).orElseThrow(() -> new IllegalArgumentException("未选择需分配的计划对象"));
        Optional.ofNullable(form.getSupervisorId()).orElseThrow(() -> new IllegalArgumentException("未填写督导人员"));
        Optional.ofNullable(form.getSupervisorDomainId()).orElseThrow(() -> new IllegalArgumentException("未填写督导领域"));
        Optional.ofNullable(form.getInspectionTime()).orElseThrow(() -> new IllegalArgumentException("未填写巡检时间"));
        //通过ID查询巡检记录，修改时间与状态为2（已分配），添加督导人员和领域
        List<TourPlan> tourPlans = tourPlanMapper.selectBatchIds(form.getIds());
        tourPlans.forEach(item -> {
            item.setStatus(TourPlanService.ALLOCATED);
            item.setSuperviseId(form.getSupervisorId());
            item.setSuperviseDomainId(form.getSupervisorDomainId());
            item.setInspectionTime(form.getInspectionTime());
            tourPlanMapper.updateById(item);
        });
        return true;
    }
}





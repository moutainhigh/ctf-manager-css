package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.pojo.dto.InspectionDTO;
import com.ctf.css.pojo.entity.Inspection;
import com.ctf.css.pojo.entity.SelfInspection;
import com.ctf.css.pojo.entity.StoreInfo;
import com.ctf.css.pojo.query.InspectionPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionVO;
import com.ctf.css.service.InspectionService;
import com.ctf.css.mapper.InspectionMapper;
import com.ctf.css.service.SelfInspectionService;
import com.ctf.css.service.StoreInfoService;
import com.ctf.css.service.TourPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author zhangyizheng
* @description 针对表【store_inspection(巡检表)】的数据库操作Service实现
* @createDate 2022-08-09 20:47:20
*/
@Service
@RequiredArgsConstructor
public class InspectionServiceImpl extends ServiceImpl<InspectionMapper, Inspection>
    implements InspectionService{

    private final StoreInfoService storeInfoService;
    private final SelfInspectionService selfInspectionService;

    @Override
    public Page<InspectionVO> pageInspection(InspectionPageQuery queryParams) {
        Page<InspectionVO> page = new Page<>();
        page.setCurrent(queryParams.getPageNum());
        page.setSize(queryParams.getPageSize());
        return this.baseMapper.getListInspectionsPage(page, queryParams);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean startInspection(InspectionDTO dto) {
        //  2022/8/20 参数校验
        Optional.ofNullable(dto).orElseThrow(() -> new IllegalStateException("未传入参数"));
        Optional.ofNullable(dto.getId()).orElseThrow(() -> new IllegalStateException("参数不可为空"));
        Optional.ofNullable(dto.getInspectionType()).orElseThrow(() -> new IllegalStateException("参数不可为空"));
        // TODO: 2022/8/20 暂时无法处理周期,方案，只可展示，未做定时任务
        // 判断是自检还是巡检,统一修改状态为 1 进行中
        if (TYPE_SELF_INSPECTION == dto.getInspectionType()) {
            //自检则查询自检表并修改数据
            return selfInspectionService.update(new LambdaUpdateWrapper<SelfInspection>().set(SelfInspection::getStatus,UNDER_WAY)
                    .eq(SelfInspection::getId,dto.getId()));
        }else {
            //巡检则查询巡检表并修改数据
            return update(new LambdaUpdateWrapper<Inspection>().set(Inspection::getStatus,UNDER_WAY)
                    .eq(Inspection::getId,dto.getId()));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteInspection(InspectionDTO dto) {
        //  2022/8/20 参数校验
        Optional.ofNullable(dto).orElseThrow(() -> new IllegalStateException("未传入参数"));
        Optional.ofNullable(dto.getId()).orElseThrow(() -> new IllegalStateException("参数不可为空"));
        Optional.ofNullable(dto.getInspectionType()).orElseThrow(() -> new IllegalStateException("参数不可为空"));
        // 判断是自检还是巡检
        if (TYPE_SELF_INSPECTION == dto.getInspectionType()) {
            //自检则查询自检表并删除数据
            return selfInspectionService.remove(new LambdaQueryWrapper<SelfInspection>().eq(SelfInspection::getId,dto.getId()));
        }else {
            //巡检则查询巡检表并删除数据
            return remove(new LambdaQueryWrapper<Inspection>().eq(Inspection::getId,dto.getId()));
        }
    }
}





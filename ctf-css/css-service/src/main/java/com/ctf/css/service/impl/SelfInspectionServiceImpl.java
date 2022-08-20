package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.pojo.entity.SelfInspection;
import com.ctf.css.pojo.entity.StoreInfo;
import com.ctf.css.pojo.query.SelfInspectionPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionVO;
import com.ctf.css.service.InspectionService;
import com.ctf.css.service.SelfInspectionService;
import com.ctf.css.mapper.SelfInspectionMapper;
import com.ctf.css.service.StoreInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zhangyizheng
* @description 针对表【store_self_inspection(门店自检表)】的数据库操作Service实现
* @createDate 2022-08-09 20:47:20
*/
@Service
@RequiredArgsConstructor
public class SelfInspectionServiceImpl extends ServiceImpl<SelfInspectionMapper, SelfInspection>
    implements SelfInspectionService{

    private final StoreInfoService storeInfoService;

    @Override
    public Page<InspectionVO> pageInspection(SelfInspectionPageQuery queryParams) {
        Page<InspectionVO> page = new Page<>();
        page.setCurrent(queryParams.getPageNum());
        page.setSize(queryParams.getPageSize());
        page = this.baseMapper.getInspectionPage(page, queryParams);
        return page;
    }

    @Override
    public boolean saveSelfInspection(String staffCodes) {
        //将门店编码字符串参数拆分
        String[] split = staffCodes.split(",|，");
        List<String> collect = Arrays.stream(split).collect(Collectors.toList());
        //通过门店编码，将对应门店加入自检,首先填写自检信息，后进行批量插入
        List<SelfInspection> list = new ArrayList<SelfInspection>();
        collect.forEach(item ->{
            SelfInspection selfInspection = new SelfInspection();
            //todo 自检的巡检时间,督导领域没有
            //插入自检表，设置门店ID，门店类型，自检状态变为 0未启动
            selfInspection.setStoreId(storeInfoService.getOne(new LambdaQueryWrapper<StoreInfo>()
                    .eq(StoreInfo::getStoreCode,item)).getId());
            selfInspection.setInspectionType(InspectionService.TYPE_SELF_INSPECTION);
            selfInspection.setStatus(InspectionService.UNSTART);
            list.add(selfInspection);
        });
        //将数据批量插入自检表
        return saveBatch(list);
    }
}





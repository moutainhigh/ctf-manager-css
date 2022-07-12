package com.zdf.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdf.auth.model.bo.AddReportsForm;
import com.zdf.auth.model.po.SysDictItem;
import com.zdf.auth.model.vo.ReportsVO;
import com.zdf.auth.service.ISysDictItemService;
import com.zdf.common.BaseResult;
import com.zdf.common.UserOperator;
import com.zdf.common.entity.InitComboxStrVO;
import com.zdf.common.utils.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.zdf.common.utils.CurrentUserUtil;
import com.zdf.auth.model.po.Reports;
import com.zdf.auth.mapper.ReportsMapper;
import com.zdf.auth.service.IReportsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

/**
 *  服务实现类
 *
 * @author jayud
 * @since 2022-04-25
 */
@Slf4j
@Service
public class ReportsServiceImpl extends ServiceImpl<ReportsMapper, Reports> implements IReportsService {


    @Autowired
    private ReportsMapper reportsMapper;

    @Autowired
    private ISysDictItemService selectItemByDictCode;

    @Override
    public IPage<Reports> selectPage(Reports reports,
                                        Integer currentPage,
                                        Integer pageSize,
                                        HttpServletRequest req){

        Page<Reports> page=new Page<Reports>(currentPage,pageSize);
        IPage<Reports> pageList= reportsMapper.pageList(page, reports);
        return pageList;
    }

    @Override
    public List<Reports> selectList(Reports reports){
        return reportsMapper.list(reports);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id){
        reportsMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(List<Long> ids){
        List<Reports> reports = new ArrayList<>();
        for (Long id : ids) {
            Reports report = new Reports();
            report.setId(id);
            report.setDeletedUserId(CurrentUserUtil.getUserDetail().getId().intValue()).setDeleteTime(LocalDateTime.now()).setDeleteUserName(CurrentUserUtil.getUsername())
                    .setIsDeleted(true);
            reports.add(report);
        }
        this.saveOrUpdateBatch(reports);
    }


    @Override
    public List<LinkedHashMap<String, Object>> queryReportsForExcel(Map<String, Object> paramMap) {
        return this.baseMapper.queryReportsForExcel(paramMap);
    }

    @Override
    public boolean saveOrUpdateReports(AddReportsForm form) {

        Reports reports = ConvertUtil.convert(form, Reports.class);
        if(form.getId() != null){
            reports.setCreateUserId(CurrentUserUtil.getUserDetail().getId().intValue());
            reports.setCreateTime(new Date());
            reports.setCreateBy(CurrentUserUtil.getUsername());
        }else{
            reports.setUpdateId(CurrentUserUtil.getUserDetail().getId().intValue());
            reports.setUpdateTime(new Date());
            reports.setUpdateBy(CurrentUserUtil.getUsername());
        }
        boolean result = this.saveOrUpdate(reports);
        if(result){
            log.warn("打印报表增加或修改成功");
        }
        return result;
    }

    @Override
    public List<ReportsVO> getReportsByMenuCode(String actionCode) {
        QueryWrapper<Reports> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Reports::getMenuCode,actionCode);
        queryWrapper.lambda().eq(Reports::getIsDeleted,0);
        List<Reports> reports = this.list(queryWrapper);
        List<ReportsVO> reportsVOS = ConvertUtil.convertList(reports, ReportsVO.class);
        return reportsVOS;
    }

    @Override
    public BaseResult getCheckData() {
        Map<String,Object> map = new HashMap<>();
        //业务类型
        List<SysDictItem> paymentMethod = selectItemByDictCode.selectItemByDictCode("model_type");
        if (CollectionUtil.isNotEmpty(paymentMethod)){
            map.put("modelType",paymentMethod);
        }
        return BaseResult.ok(map);
    }

}

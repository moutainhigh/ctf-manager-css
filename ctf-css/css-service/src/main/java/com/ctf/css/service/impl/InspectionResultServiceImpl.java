package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.css.converter.InspectionConverter;
import com.ctf.css.pojo.entity.Inspection;
import com.ctf.css.pojo.entity.InspectionResult;
import com.ctf.css.pojo.entity.Rectification;
import com.ctf.css.pojo.form.SuperviseForm;
import com.ctf.css.pojo.query.RestultPageQuery;
import com.ctf.css.pojo.vo.ex.InspectionResultVO;
import com.ctf.css.service.InspectionResultService;
import com.ctf.css.mapper.InspectionResultMapper;
import com.ctf.css.service.InspectionService;
import com.ctf.css.service.RectificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author zhangyizheng
* @description 针对表【store_inspection_result(巡检结果表)】的数据库操作Service实现
* @createDate 2022-08-21 11:20:58
*/
@Service
@RequiredArgsConstructor
public class InspectionResultServiceImpl extends ServiceImpl<InspectionResultMapper, InspectionResult>
    implements InspectionResultService{

    private final InspectionService inspectionService;
    private final InspectionConverter inspectionConverter;
    private final RectificationService rectificationService;

    @Override
    public Page<InspectionResultVO> pageInspectionResult(RestultPageQuery queryParams) {
        Page<InspectionResultVO> page = new Page<>();
        page.setCurrent(queryParams.getPageNum());
        page.setSize(queryParams.getPageSize());
        return this.baseMapper.pageInspectionResult(page, queryParams);
    }

    @Override
    public boolean isRectification(Long id) {
        // 2022/8/22 通过巡检ID， 查询巡检信息
        InspectionResult inspectionResult = getById(id);
        Inspection inspection = inspectionService.getById(inspectionResult.getInspectionId());
        // TODO: 2022/8/22 查干此巡检记录，是否需要整改
        Rectification rectification = inspectionConverter.Inspection2Rectification(inspection);
        rectification.setInspectionId(id);
        rectification.setId(null);
        // TODO: 2022/8/22 将信息添加到整改结果中 修改状态，添加整改项
        return rectificationService.save(rectification);
    }

}





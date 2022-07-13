package com.ctf.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.auth.model.bo.AddCostTypeForm;
import com.ctf.auth.model.bo.QueryCostTypeForm;
import com.ctf.auth.model.enums.StatusEnum;
import com.ctf.auth.model.vo.CostTypeVO;
import com.ctf.common.UserOperator;
import com.ctf.common.utils.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.ctf.common.utils.CurrentUserUtil;
import com.ctf.auth.model.po.CostType;
import com.ctf.auth.mapper.CostTypeMapper;
import com.ctf.auth.service.ICostTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 费用类别 服务实现类
 *
 * @author jayud
 * @since 2022-04-11
 */
@Slf4j
@Service
public class CostTypeServiceImpl extends ServiceImpl<CostTypeMapper, CostType> implements ICostTypeService {


    @Autowired
    private CostTypeMapper costTypeMapper;

    @Override
    public IPage<CostType> selectPage(CostType costType,
                                        Integer currentPage,
                                        Integer pageSize,
                                        HttpServletRequest req){

        Page<CostType> page=new Page<CostType>(currentPage,pageSize);
        IPage<CostType> pageList= costTypeMapper.pageList(page, costType);
        return pageList;
    }

    @Override
    public List<CostType> selectList(CostType costType){
        return costTypeMapper.list(costType);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id){
        costTypeMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id){
        costTypeMapper.logicDel(id,CurrentUserUtil.getUsername());
    }


    @Override
    public List<LinkedHashMap<String, Object>> queryCostTypeForExcel(Map<String, Object> paramMap) {
        return this.baseMapper.queryCostTypeForExcel(paramMap);
    }

    /**
     * 获取启用费用类别
     */
    @Override
    public List<CostType> getEnableCostType() {
        QueryWrapper<CostType> condition = new QueryWrapper<>();
        condition.lambda().eq(CostType::getStatus, StatusEnum.ENABLE.getCode());
        return this.baseMapper.selectList(condition);
    }

    /**
     * 列表分页查询
     *
     * @param form
     * @return
     */
    @Override
    public IPage<CostTypeVO> findCostTypeByPage(QueryCostTypeForm form) {
        Page<CostType> page = new Page(form.getPageNum(), form.getPageSize());
        return this.baseMapper.findCostTypeByPage(page, form);
    }

    /**
     * 根据id集合查询费用类型
     */
    @Override
    public List<CostTypeVO> getCostTypeByIds(List<Long> ids) {
        List<CostTypeVO> list = new ArrayList<>();
        List<CostType> costTypes = this.baseMapper.selectBatchIds(ids);
        for (CostType costType : costTypes) {
            CostTypeVO costTypeVO = ConvertUtil.convert(costType, CostTypeVO.class);
            list.add(costTypeVO);
        }

        return list;
    }

    /**
     * 新增编辑费用类别
     *
     * @param form
     * @return
     */
    @Override
    public boolean saveOrUpdateCostType(AddCostTypeForm form) {
        CostType costType = ConvertUtil.convert(form, CostType.class);

        //判断是否代收代垫， 是-费用类别后面加-Y，否则-N
//        String sign = form.getIsPayCollection() ? "-Y" : "-N";

        if (Objects.isNull(costType.getId())) {
            costType
//                    .setCodeName(form.getCodeName() + sign)
                    .setCodeName(form.getCodeName())
                    .setCreateTime(new Date())
                    .setCreateBy(CurrentUserUtil.getUsername());
            return this.save(costType);
        } else {
//            String str = form.getCodeName().substring(0, form.getCodeName().indexOf("-"));
            costType.setCodeName(form.getCodeName())
//                    .setCodeName(str + sign)
                    .setCode(null)
                    .setUpdateTime(new Date())
                    .setUpdateBy(CurrentUserUtil.getUsername());
            return this.updateById(costType);
        }
    }

    /**
     * 更改启用/禁用状态
     *
     * @param id
     * @return
     */
    @Override
    public boolean enableOrDisableCostType(Long id) {
        //查询当前状态
        QueryWrapper<CostType> condition = new QueryWrapper<>();
        condition.lambda().select(CostType::getStatus).eq(CostType::getId, id);
        CostType tmp = this.baseMapper.selectOne(condition);

        String status = "1".equals(tmp.getStatus()) ? StatusEnum.INVALID.getCode() : StatusEnum.ENABLE.getCode();

        CostType costType = new CostType();
        costType.setId(id);
        costType.setStatus(status).setUpdateTime(new Date()).setUpdateBy(CurrentUserUtil.getUsername());

        return this.updateById(costType);
    }

    /**
     * 校验唯一性
     *
     * @return
     */
    @Override
    public boolean checkUnique(CostType costType) {
        QueryWrapper<CostType> condition = new QueryWrapper<>();
        if (costType.getId() != null) {
            //修改过滤自身名字
            condition.lambda().and(tmp -> tmp.eq(CostType::getId, costType.getId())
                    .eq(CostType::getCodeName, costType.getCodeName()));
            int count = this.count(condition);
            //匹配到自己名称,不进行唯一校验
            if (count == 0) {
                condition = new QueryWrapper<>();
                condition.lambda().eq(CostType::getCodeName, costType.getCodeName());
                return this.count(condition) > 0;
            } else {
                return false;
            }
        } else {
            condition.lambda().eq(CostType::getCode, costType.getCode())
                    .or().eq(CostType::getCodeName, costType.getCodeName());
            return this.count(condition) > 0;
        }

    }

    @Override
    public List<CostType> getByCondition(CostType costType) {
        QueryWrapper<CostType> condition = new QueryWrapper<>(costType);
        return this.baseMapper.selectList(condition);
    }

}

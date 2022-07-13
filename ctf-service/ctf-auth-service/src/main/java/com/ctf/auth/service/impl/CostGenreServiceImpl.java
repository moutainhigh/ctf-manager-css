package com.ctf.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.auth.model.bo.AddCostGenreForm;
import com.ctf.auth.model.bo.QueryCostGenreForm;
import com.ctf.auth.model.enums.StatusEnum;
import com.ctf.auth.model.po.CostGenreTaxRate;
import com.ctf.auth.model.vo.CostGenreVO;
import com.ctf.auth.service.ICostGenreTaxRateService;
import com.ctf.common.UserOperator;
import com.ctf.common.utils.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.ctf.common.utils.CurrentUserUtil;
import com.ctf.auth.model.po.CostGenre;
import com.ctf.auth.mapper.CostGenreMapper;
import com.ctf.auth.service.ICostGenreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 基础数据费用类型 服务实现类
 *
 * @author jayud
 * @since 2022-04-11
 */
@Slf4j
@Service
public class CostGenreServiceImpl extends ServiceImpl<CostGenreMapper, CostGenre> implements ICostGenreService {


    @Autowired
    private CostGenreMapper costGenreMapper;

    @Autowired
    private ICostGenreTaxRateService costGenreTaxRateService;

    @Override
    public IPage<CostGenre> selectPage(CostGenre costGenre,
                                        Integer currentPage,
                                        Integer pageSize,
                                        HttpServletRequest req){

        Page<CostGenre> page=new Page<CostGenre>(currentPage,pageSize);
        IPage<CostGenre> pageList= costGenreMapper.pageList(page, costGenre);
        return pageList;
    }

    @Override
    public List<CostGenre> selectList(CostGenre costGenre){
        return costGenreMapper.list(costGenre);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id){
        costGenreMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id){
        costGenreMapper.logicDel(id,CurrentUserUtil.getUsername());
    }


    @Override
    public List<LinkedHashMap<String, Object>> queryCostGenreForExcel(Map<String, Object> paramMap) {
        return this.baseMapper.queryCostGenreForExcel(paramMap);
    }

    /**
     * 分页查询费用类型
     */
    @Override
    public IPage<CostGenreVO> findCostGenreByPage(QueryCostGenreForm form) {
        IPage<CostGenreVO> iPage = this.baseMapper.findCostGenreByPage(new Page(form.getPageNum(), form.getPageSize()), form);
        return iPage;
    }

    /**
     * 新增编辑费用类型
     */
    @Override
    public boolean saveOrUpdateCostGenre(AddCostGenreForm form) {
        CostGenre costGenre = ConvertUtil.convert(form, CostGenre.class);
        if (Objects.isNull(costGenre.getId())) {
            costGenre.setCreateTime(new Date())
                    .setCreateBy(CurrentUserUtil.getUsername());
            return this.save(costGenre);
        } else {
            costGenre.setCode(null)
                    .setUpdateTime(new Date())
                    .setUpdateBy(CurrentUserUtil.getUsername());
            return this.updateById(costGenre);
        }
    }

    /**
     * 更改启用/禁用费用类型状态
     *
     * @param id
     * @return
     */
    @Override
    public boolean enableOrDisableCostGenre(Long id) {
        //查询当前状态
        QueryWrapper<CostGenre> condition = new QueryWrapper<>();
        condition.lambda().select(CostGenre::getStatus).eq(CostGenre::getId, id);
        CostGenre tmp = this.baseMapper.selectOne(condition);

        String status = "1".equals(tmp.getStatus()) ? StatusEnum.INVALID.getCode() : StatusEnum.ENABLE.getCode();

        CostGenre costGenre = new CostGenre();
        costGenre.setStatus(status);
        costGenre.setId(id).setUpdateTime(new Date())
                .setUpdateBy(CurrentUserUtil.getUsername());;

        return this.updateById(costGenre);
    }

    /**
     * 根据id查询费用类型
     */
    @Override
    public CostGenreVO getById(Long id) {
        CostGenre costGenre = this.baseMapper.selectById(id);
        return ConvertUtil.convert(costGenre, CostGenreVO.class);
    }

    /**
     * 根据id集合查询费用类型
     */
    @Override
    public List<CostGenre> getByIds(List<Long> ids) {
        return this.baseMapper.selectBatchIds(ids);
    }

    /**
     * 获取启用费用类型
     */
    @Override
    public List<CostGenre> getEnableCostGenre() {
        QueryWrapper<CostGenre> condition = new QueryWrapper<>();
        condition.lambda().eq(CostGenre::getStatus, StatusEnum.ENABLE.getCode());
        return this.baseMapper.selectList(condition);
    }

    /**
     * 校验费用类型唯一性
     *
     * @return
     */
    @Override
    public boolean checkUnique(CostGenre costGenre) {
        QueryWrapper<CostGenre> condition = new QueryWrapper<>();
        if (costGenre.getId() != null) {
            //修改过滤自身名字
            condition.lambda().and(tmp -> tmp.eq(CostGenre::getId, costGenre.getId())
                    .eq(CostGenre::getName, costGenre.getName()));
            int count = this.count(condition);
            //匹配到自己名称,不进行唯一校验
            if (count == 0) {
                condition = new QueryWrapper<>();
                condition.lambda().eq(CostGenre::getName, costGenre.getName());
                return this.count(condition) > 0;
            } else {
                return false;
            }
        } else {
            condition.lambda().eq(CostGenre::getCode, costGenre.getCode())
                    .or().eq(CostGenre::getName, costGenre.getName());
            return this.count(condition) > 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(AddCostGenreForm form) {
        CostGenre costGenre = ConvertUtil.convert(form, CostGenre.class);
        boolean opt = true;
        if (Objects.isNull(costGenre.getId())) {
            costGenre.setCreateTime(new Date())
                    .setCreateBy(CurrentUserUtil.getUsername());
            this.save(costGenre);
            List<CostGenreTaxRate> genreTaxRates = form.getCostGenreTaxRates();
            if (!CollectionUtils.isEmpty(genreTaxRates)) {
                genreTaxRates.forEach(e -> e.setCostGenreId(costGenre.getId()));
                opt = costGenreTaxRateService.saveBatch(genreTaxRates);
            }
            return opt;
        } else {
            costGenre.setCode(null)
                    .setUpdateTime(new Date())
                    .setUpdateBy(CurrentUserUtil.getUsername());
            opt = this.updateById(costGenre);
            List<CostGenreTaxRate> genreTaxRates = form.getCostGenreTaxRates();
            this.costGenreTaxRateService.remove(new QueryWrapper<>(new CostGenreTaxRate().setCostGenreId(costGenre.getId())));
            if (!CollectionUtils.isEmpty(genreTaxRates)) {
                genreTaxRates.forEach(e -> e.setCostGenreId(costGenre.getId()));
                opt = costGenreTaxRateService.saveBatch(genreTaxRates);
            }
            return opt;
        }
    }

}

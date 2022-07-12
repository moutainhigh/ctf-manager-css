package com.zdf.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zdf.auth.mapper.ProductBizMapper;
import com.zdf.auth.model.bo.AddProductBizForm;
import com.zdf.auth.model.bo.QueryProductBizForm;
import com.zdf.auth.model.enums.StatusEnum;
import com.zdf.auth.model.po.CostGenre;
import com.zdf.auth.model.po.ProductBiz;
import com.zdf.auth.model.vo.CostGenreVO;
import com.zdf.auth.model.vo.ProductBizVO;
import com.zdf.auth.service.ICostGenreService;
import com.zdf.auth.service.IProductBizService;
import com.zdf.common.UserOperator;
import com.zdf.common.utils.ConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class ProductBizServiceImpl extends ServiceImpl<ProductBizMapper, ProductBiz> implements IProductBizService {

    @Autowired
    private ICostGenreService costGenreService;


    @Override
    public List<ProductBiz> findProductBiz() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", "1");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public ProductBiz getProductBizByCode(String idCode) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", "1");//有效的
        queryWrapper.eq("id_code", idCode);
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 列表分页查询
     *
     * @param form
     * @return
     */
    @Override
    public IPage<ProductBizVO> findProductBizByPage(QueryProductBizForm form) {
        Page<ProductBiz> page = new Page(form.getPageNum(), form.getPageSize());
        IPage<ProductBizVO> iPage = this.baseMapper.findProductBizByPage(page, form);
        //根据费用类型id集合查询费用类型名称

        for (ProductBizVO record : iPage.getRecords()) {
            if (StringUtils.isEmpty(record.getCostGenreIds())) {
                continue;
            }
            String[] tmp = record.getCostGenreIds().split(",");
            List<Long> cids = new ArrayList<>(tmp.length);
            for (String id : tmp) {
                cids.add(Long.parseLong(id));
            }
            List<CostGenre> costGenres = this.costGenreService.getByIds(cids);
            List<CostGenreVO> costGenreVOs = new ArrayList<>();
            for (CostGenre costGenre : costGenres) {
                costGenreVOs.add(ConvertUtil.convert(costGenre, CostGenreVO.class));
            }
            record.setCostGenreVOs(costGenreVOs);
        }

        return iPage;
    }

    /**
     * 根据id查询业务类型
     */
    @Override
    public ProductBizVO getById(Long id) {
        ProductBiz productBiz = this.baseMapper.selectById(id);
        ProductBizVO productBizVO = ConvertUtil.convert(productBiz, ProductBizVO.class);
        if (StringUtils.isNotEmpty(productBizVO.getCostGenreIds())) {
            String[] tmp = productBizVO.getCostGenreIds().split(",");
            List<Long> cids = new ArrayList<>();
            for (String cid : tmp) {
                cids.add(Long.parseLong(cid));
            }
            List<CostGenre> costGenres = this.costGenreService.getByIds(cids);
            List<CostGenreVO> costGenreVOs = new ArrayList<>();
            for (CostGenre costGenre : costGenres) {
                costGenreVOs.add(ConvertUtil.convert(costGenre, CostGenreVO.class));
            }
            productBizVO.setCostGenreVOs(costGenreVOs);
        }

        return productBizVO;
    }

    /**
     * 新增编辑业务类型
     *
     * @param form
     * @return
     */
    @Override
    public boolean saveOrUpdateProductBiz(AddProductBizForm form) {
        //拼接费用类型id
        StringBuilder sb = new StringBuilder();
        for (CostGenreVO costGenreVO : form.getCostGenreVOs()) {
            sb.append(costGenreVO.getId()).append(",");
        }
        ProductBiz productBiz = ConvertUtil.convert(form, ProductBiz.class);
        productBiz.setCostGenreIds(sb.substring(0, sb.length() - 1));

        if (Objects.isNull(productBiz.getId())) {
            productBiz.setCreateTime(LocalDateTime.now())
                    .setCreateUser(UserOperator.getToken());
            return this.save(productBiz);
        } else {
            productBiz.setIdCode(null)
                    .setUpdateTime(LocalDateTime.now())
                    .setUpdateUser(UserOperator.getToken());
            return this.updateById(productBiz);
        }
    }

    /**
     * 更改启用/禁用业务类型状态
     *
     * @param id
     * @return
     */
    @Override
    public boolean enableOrDisableProductBiz(Long id) {
        //查询当前状态
        QueryWrapper<ProductBiz> condition = new QueryWrapper<>();
        condition.lambda().select(ProductBiz::getStatus).eq(ProductBiz::getId, id);
        ProductBiz tmp = this.baseMapper.selectOne(condition);

        String status = "1".equals(tmp.getStatus()) ? StatusEnum.INVALID.getCode() : StatusEnum.ENABLE.getCode();

        ProductBiz productBiz = new ProductBiz().setId(id).setStatus(status)
                .setUpdateTime(LocalDateTime.now()).setUpdateUser(UserOperator.getToken());

        return this.updateById(productBiz);
    }

    /**
     * 校验唯一性
     *
     * @return
     */
    @Override
    public boolean checkUnique(ProductBiz productBiz) {
        QueryWrapper<ProductBiz> condition = new QueryWrapper<>();
        if (productBiz.getId() != null) {
            //修改过滤自身名字
            condition.lambda().and(tmp -> tmp.eq(ProductBiz::getId, productBiz.getId())
                    .eq(ProductBiz::getName, productBiz.getName()));
            int count = this.count(condition);
            if (count == 0) {
                condition = new QueryWrapper<>();
                condition.lambda().eq(ProductBiz::getName, productBiz.getName());
                return this.count(condition) > 0;
            } else {
                return false;
            }
        } else {
            condition.lambda().eq(ProductBiz::getIdCode, productBiz.getIdCode())
                    .or().eq(ProductBiz::getName, productBiz.getName());
            return this.count(condition) > 0;
        }

    }

    /**
     * 获取启用业务类型
     */
    @Override
    public List<ProductBiz> getEnableProductBiz() {
        QueryWrapper<ProductBiz> condition = new QueryWrapper<>();
        condition.lambda().eq(ProductBiz::getStatus, StatusEnum.ENABLE.getCode());
        return this.baseMapper.selectList(condition);
    }

    /**
     * 根据业务名称获取业务编码
     * @param name
     * @return
     */
    @Override
    public String getProductBizIdCodeByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id_code");
        queryWrapper.eq("status", "1");//有效的
        queryWrapper.eq("name", name);
        ProductBiz productBiz = baseMapper.selectOne(queryWrapper);
        return productBiz != null ? productBiz.getIdCode() : null;
    }

}

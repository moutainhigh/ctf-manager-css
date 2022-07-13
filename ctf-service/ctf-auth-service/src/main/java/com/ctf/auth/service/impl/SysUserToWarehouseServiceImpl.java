package com.ctf.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.auth.feign.WmsClient;
import com.ctf.auth.model.bo.SysUserToWarehouseForm;
import com.ctf.common.BaseResult;
import com.ctf.common.utils.ConvertUtil;
import com.ctf.common.utils.ListUtils;
import com.ctf.common.utils.StringUtils;
import com.ctf.wms.model.bo.MaterialForm;
import com.ctf.wms.model.enums.MaterialStatusEnum;
import com.ctf.wms.model.po.Material;
import com.ctf.wms.model.po.Warehouse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.ctf.common.utils.CurrentUserUtil;
import com.ctf.auth.model.po.SysUserToWarehouse;
import com.ctf.auth.mapper.SysUserToWarehouseMapper;
import com.ctf.auth.service.ISysUserToWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户与仓库关联表 服务实现类
 *
 * @author jayud
 * @since 2022-04-08
 */
@Slf4j
@Service
public class SysUserToWarehouseServiceImpl extends ServiceImpl<SysUserToWarehouseMapper, SysUserToWarehouse> implements ISysUserToWarehouseService {


    @Autowired
    private SysUserToWarehouseMapper sysUserToWarehouseMapper;

    @Autowired
    private WmsClient wmsClient;

    @Override
    public IPage<SysUserToWarehouse> selectPage(SysUserToWarehouse sysUserToWarehouse,
                                                Integer currentPage,
                                                Integer pageSize,
                                                HttpServletRequest req) {

        Page<SysUserToWarehouse> page = new Page<SysUserToWarehouse>(currentPage, pageSize);
        IPage<SysUserToWarehouse> pageList = sysUserToWarehouseMapper.pageList(page, sysUserToWarehouse);
        return pageList;
    }

    @Override
    public List<SysUserToWarehouse> selectList(SysUserToWarehouse sysUserToWarehouse) {
        return sysUserToWarehouseMapper.list(sysUserToWarehouse);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id) {
        sysUserToWarehouseMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id) {
        sysUserToWarehouseMapper.logicDel(id, CurrentUserUtil.getUsername());
    }


    @Override
    public List<LinkedHashMap<String, Object>> querySysUserToWarehouseForExcel(Map<String, Object> paramMap) {
        return this.baseMapper.querySysUserToWarehouseForExcel(paramMap);
    }

    @Override
    public BaseResult saveWarehouse(Long userId, List<SysUserToWarehouse> warehouseList) {
        //上次关联数据
        LambdaQueryWrapper<SysUserToWarehouse> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUserToWarehouse::getIsDeleted, false);
        lambdaQueryWrapper.eq(SysUserToWarehouse::getUserId, userId);
        List<SysUserToWarehouse> lastWarehouseList = this.list(lambdaQueryWrapper);

        //这次关联的id

        //id为空新增
        List<SysUserToWarehouse> addList = new ArrayList<>();
        List<String> thisIdList = new ArrayList<>();
        if (CollUtil.isNotEmpty(warehouseList)) {
            addList = warehouseList.stream().filter(x -> x.getId() == null).collect(Collectors.toList());
            thisIdList = warehouseList.stream().filter(x -> x.getId() != null).map(x -> String.valueOf(x.getId())).collect(Collectors.toList());
        }
        //删除id集合
        List<String> delIdList = new ArrayList<>();
        if (CollUtil.isNotEmpty(lastWarehouseList)) {
            List<String> lastIdList = lastWarehouseList.stream().map(x -> String.valueOf(x.getId())).collect(Collectors.toList());
            delIdList = ListUtils.getDiff(thisIdList, lastIdList);
        }
        if (CollUtil.isNotEmpty(addList)) {
            addList.forEach(msg -> {
                msg.setUserId(userId);
            });
            this.saveBatch(addList);
        }
        if (CollUtil.isNotEmpty(delIdList)) {
            sysUserToWarehouseMapper.logicDelByIds(delIdList, CurrentUserUtil.getUsername());
        }
        return BaseResult.ok();
    }

    @Override
    public BaseResult saveUpdateWarehouse(Long userId, List<SysUserToWarehouseForm> sysUserToWarehouselist, List<Long> warehouseIdLists) {

        LambdaQueryWrapper<SysUserToWarehouse> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUserToWarehouse::getIsDeleted, false);
        lambdaQueryWrapper.eq(SysUserToWarehouse::getUserId, userId);
        List<SysUserToWarehouse> lastWarehouseList = this.list(lambdaQueryWrapper);

        //上次选中（之前的）
        List<String> lastIdList = lastWarehouseList.stream().map(x -> String.valueOf(x.getWarehouseId())).collect(Collectors.toList());

        // 本次（传进来的当前的）
        List<String> collect = warehouseIdLists.stream().map(x -> String.valueOf(x)).collect(Collectors.toList());

        //需要添加的
        List<String> add = ListUtils.getDiff(lastIdList, collect);

        //需要删除的
        List<String> del = ListUtils.getDiff(collect, lastIdList);

        if (CollUtil.isNotEmpty(add)) {
            List<SysUserToWarehouseForm> sysUserToWarehouses = ConvertUtil.convertList(sysUserToWarehouselist, SysUserToWarehouseForm.class);

            //需要赋值进去的 信息
            List<SysUserToWarehouseForm> addList = sysUserToWarehouses.stream().filter(x -> add.contains(String.valueOf(x.getWarehouseId()))).collect(Collectors.toList());

            addList.stream().forEach(x -> {
                x.setUserId(userId);
                x.setWarehouseName(x.getName());
                x.setWarehouseCode(x.getCode());
            });
            List<SysUserToWarehouse> sysUserToWarehouses1 = ConvertUtil.convertList(addList, SysUserToWarehouse.class);
            this.saveOrUpdateBatch(sysUserToWarehouses1);
        }

        if (CollUtil.isNotEmpty(del)) {
            List<Long> collect1 = del.stream().map(x -> Long.parseLong(x)).collect(Collectors.toList());
            String fileNameString = StringUtils.getFileNameString(collect1);
            //删除的 数据
            LambdaQueryWrapper<SysUserToWarehouse> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper1.in(SysUserToWarehouse::getWarehouseId, fileNameString);
            lambdaQueryWrapper1.eq(SysUserToWarehouse::getIsDeleted, false);
            lambdaQueryWrapper1.eq(SysUserToWarehouse::getUserId, userId);
            List<SysUserToWarehouse> lastWarehouseList1 = this.list(lambdaQueryWrapper1);

            List<Long> collect2 = lastWarehouseList1.stream().map(x -> x.getId()).collect(Collectors.toList());
            List<String> collect3 = collect2.stream().map(x -> String.valueOf(x)).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(del)) {
                sysUserToWarehouseMapper.logicDelByIds(collect3, CurrentUserUtil.getUsername());
            }
        }
        return BaseResult.ok();
    }

    @Override
    public List<SysUserToWarehouse> getWarehouseMsg() {
        List<SysUserToWarehouse> warehouseList = new ArrayList<>();
        BaseResult<List<Warehouse>> warehouseResult = wmsClient.selectWarehouseList(CurrentUserUtil.getUserTenantCode(), 1);
        if (CollUtil.isNotEmpty(warehouseResult.getResult())) {
            warehouseResult.getResult().forEach(warehouse -> {
                SysUserToWarehouse sysUserToWarehouse = new SysUserToWarehouse();
                sysUserToWarehouse.setWarehouseId(warehouse.getId());
                sysUserToWarehouse.setWarehouseCode(warehouse.getCode());
                sysUserToWarehouse.setWarehouseName(warehouse.getName());
                warehouseList.add(sysUserToWarehouse);
            });
        }
        return warehouseList;
    }


    public static void main(String[] args) {
        //本次（传进来的当前的）
        List<String> one = new ArrayList<>();
        one.add("1");
        one.add("2");


        //上次选中（之前的）
        List<String> two = new ArrayList<>();
        two.add("1");
        two.add("2");
        two.add("3");

//        List<String> add = ListUtils.getDiff(two,one);
        List<String> del = ListUtils.getDiff(one, two);


//        System.out.println("不同的添加的 ："+add);
        System.out.println("不同的添加的 ：" + del);
        System.out.println("-------------------");


        //本次（传进来的当前的）
        List<String> one1 = new ArrayList<>();
        one.add("1");
        one.add("2");


        //上次选中（之前的）
        List<String> two2 = new ArrayList<>();
        two.add("1");
        two.add("2");
        one.add("3");

//        List<String> del = ListUtils.getDiff(two2,one1);
//        System.out.println("删除的 ："+del);
    }
}

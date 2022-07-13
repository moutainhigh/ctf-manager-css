package com.ctf.auth.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctf.auth.model.bo.CheckForm;
import com.ctf.auth.model.po.BNoRule;
import com.ctf.auth.model.po.SysRoleActionCheck;
import com.ctf.auth.model.vo.BPublicCheckVO;
import com.ctf.auth.model.vo.SysRoleActionCheckVO;
import com.ctf.auth.model.vo.SysUserVO;
import com.ctf.auth.service.*;
import com.ctf.common.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.ctf.common.utils.CurrentUserUtil;
import com.ctf.auth.model.po.BPublicCheck;
import com.ctf.auth.mapper.BPublicCheckMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 审核记录表 服务实现类
 *
 * @author jayud
 * @since 2022-02-28
 */
@Slf4j
@Service
public class BPublicCheckServiceImpl extends ServiceImpl<BPublicCheckMapper, BPublicCheck> implements IPublicCheckService {


    @Autowired
    private BPublicCheckMapper bPublicCheckMapper;

    @Autowired
    private IBNoRuleService bNoRuleService;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleActionCheckService sysRoleActionCheckService;

    @Override
    public IPage<BPublicCheckVO> selectPage(BPublicCheck bPublicCheck,
                                        Integer currentPage,
                                        Integer pageSize,
                                        HttpServletRequest req){

        Page<BPublicCheckVO> page=new Page<BPublicCheckVO>(currentPage,pageSize);
        IPage<BPublicCheckVO> pageList= bPublicCheckMapper.pageList(page, bPublicCheck);
        return pageList;
    }

    @Override
    public List<BPublicCheckVO> selectList(BPublicCheck bPublicCheck){
        return bPublicCheckMapper.list(bPublicCheck);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void phyDelById(Long id){
        bPublicCheckMapper.phyDelById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logicDel(Long id){
        bPublicCheckMapper.logicDel(id,CurrentUserUtil.getUsername());
    }

    @Override
    public BaseResult check(CheckForm checkForm) {
        //根据当前单据编号获取表名、库名
        BNoRule bNoRule = bNoRuleService.getNoRulesBySheetCode(checkForm.getSheetCode());
        String checkTable = bNoRule.getCheckTable();

        //获取当前登录用户
        SysUserVO systemUserByName = sysUserService.getSystemUserByName(CurrentUserUtil.getUsername());
        //根据id获取当前记录
        Map<String,Object> map = new HashMap<>();
        if(null == bNoRule.getCheckDatabase()){
            map  = bPublicCheckMapper.getData(checkForm.getRecordId(),checkTable);
        }else{
            map  = bPublicCheckMapper.getCheckData(checkForm.getRecordId(),checkTable,bNoRule.getCheckDatabase());
        }

        //判断当前单据审核状态  s_level <= s_step  已审核完成
        Integer sLevel = (Integer)map.get("f_level");
        Integer sStep = (Integer)map.get("f_step");
        Integer newStep = sStep + 1;

        //定义审核状态
        String checkFlag;
        if(newStep.equals(sLevel)){
            checkFlag = "Y";
        }else{
            checkFlag = "N"+newStep;
        }

        if(sLevel <= sStep){
            return BaseResult.error(444,"已审核完成,不能在审核");
        }
        //当前用户不为管理员，判断该用户的审核权限
        int count = sysUserRoleService.getCountByUserNameAndRoleName(systemUserByName.getName(),"super_admin",systemUserByName.getCode());
        if(count <= 0){
            //判断该用户有无审核按钮权限
            int check = sysUserRoleService.getCountByUserName(systemUserByName.getName(),systemUserByName.getCode(),checkForm.getMenuCode());
            if(check<=0){
                return BaseResult.error(444,"没有审核按钮权限");
            }
            //判断该用户是否有该级审核权限
            int checkCount = sysUserRoleService.getCountByUserNameAndStep(systemUserByName.getName(),systemUserByName.getCode(),checkForm.getMenuCode(),newStep);
            if(checkCount <= 0){
                return BaseResult.error(444,"没有该级别审核权限");
            }
            //判断是否审核金额  暂时不控制

            //判断连续两次审核能否为同一人
            if(bNoRule.getCheckUp() && sStep != 0){
                BPublicCheck bPublicCheck = this.baseMapper.getPublicCheckByRecordId(checkForm.getSheetCode(),checkForm.getRecordId(),1);
                if(bPublicCheck.getFCheckName().equals(CurrentUserUtil.getUsername())){
                    return BaseResult.error(444,"连续两次审核不能为同一人");
                }
            }
        }

        if(checkForm.getCheck()){
            //执行审核
            int update;
            if(null == bNoRule.getCheckDatabase()){
                update = bPublicCheckMapper.updateData(checkForm.getRecordId(),checkTable,newStep,checkFlag,new Date(),systemUserByName.getName());
            }else{
                update = bPublicCheckMapper.updateCheckData(checkForm.getRecordId(),checkTable,bNoRule.getCheckDatabase(),newStep,checkFlag,new Date(),systemUserByName.getName());
            }
            if(update <= 0){
                return BaseResult.error(444,"审核失败，修改审核状态失败");
            }



            //添加审核记录
            BPublicCheck bPublicCheck = new BPublicCheck();
            bPublicCheck.setSheetCode(checkForm.getSheetCode());
            bPublicCheck.setCheckFlag(1);
            bPublicCheck.setRecordId(checkForm.getRecordId());
            bPublicCheck.setMenuCode(checkForm.getMenuCode());
            bPublicCheck.setFLevel(sLevel);
            bPublicCheck.setFStep(newStep);
            bPublicCheck.setIsCheck(checkForm.getCheck());
            if(0 == sStep){
                bPublicCheck.setTimeConsuming(null);
            }else {
                BPublicCheck byRecordId = this.baseMapper.getPublicCheckByRecordId(checkForm.getSheetCode(),checkForm.getRecordId(),1);
                bPublicCheck.setTimeConsuming(this.getTimeDifference(byRecordId.getCreateTime(),new Date()));
            }
            bPublicCheck.setCheckStateFlag(checkFlag);
            bPublicCheck.setFCheckId(systemUserByName.getId());
            bPublicCheck.setFCheckName(systemUserByName.getName());
            bPublicCheck.setCreateBy(systemUserByName.getName());
            bPublicCheck.setCreateTime(new Date());
            boolean save = this.save(bPublicCheck);
            if(!save){
                return BaseResult.error(444,"审核失败，添加审核数据失败");
            }
            log.warn("审核成功");
            return BaseResult.ok("审核成功");
        }else{

            //添加审核记录
            BPublicCheck bPublicCheck = new BPublicCheck();
            bPublicCheck.setSheetCode(checkForm.getSheetCode());
            bPublicCheck.setCheckFlag(1);
            bPublicCheck.setRecordId(checkForm.getRecordId());
            bPublicCheck.setMenuCode(checkForm.getMenuCode());
            bPublicCheck.setFLevel(sLevel);
            bPublicCheck.setFStep(newStep);
            bPublicCheck.setRemark(checkForm.getCheckRemark());
            bPublicCheck.setCheckStateFlag(checkFlag);
            bPublicCheck.setIsCheck(checkForm.getCheck());
            if(0 == sStep){
                bPublicCheck.setTimeConsuming(null);
            }else {
                BPublicCheck byRecordId = this.baseMapper.getPublicCheckByRecordId(checkForm.getSheetCode(),checkForm.getRecordId(),1);
                bPublicCheck.setTimeConsuming(this.getTimeDifference(byRecordId.getCreateTime(),new Date()));
            }
            bPublicCheck.setFCheckId(systemUserByName.getId());
            bPublicCheck.setFCheckName(systemUserByName.getName());
            bPublicCheck.setCreateBy(systemUserByName.getName());
            bPublicCheck.setCreateTime(new Date());
            boolean save = this.save(bPublicCheck);
            if(!save){
                return BaseResult.error(444,"审核失败，添加审核数据失败");
            }
            log.warn("审核成功");
            return BaseResult.ok("审核成功");
        }


    }

    @Override
    public BaseResult unCheck(CheckForm checkForm) {

        //根据当前单据编号获取表名、库名
        BNoRule bNoRule = bNoRuleService.getNoRulesBySheetCode(checkForm.getSheetCode());
        String checkTable = bNoRule.getCheckTable();

        //获取当前登录用户
        SysUserVO systemUserByName = sysUserService.getSystemUserByName(CurrentUserUtil.getUsername());

        //根据id获取当前记录
        Map<String,Object> map = new HashMap<>();
        if(null == bNoRule.getCheckDatabase()){
            map  = bPublicCheckMapper.getData(checkForm.getRecordId(),checkTable);
        }else{
            map  = bPublicCheckMapper.getCheckData(checkForm.getRecordId(),checkTable,bNoRule.getCheckDatabase());
        }
        //判断当前审核步长  为0 当前订单未审核
        Integer sLevel = (Integer)map.get("f_level");
        Integer sStep = (Integer)map.get("f_step");
        Integer newStep = 0;
        //定义审核状态
        String checkFlag = "";
        if(0 == bNoRule.getDeApprovalSetting()){
            sStep = 0;
            checkFlag = "N0";
        }else{
            newStep = sStep - 1;
            checkFlag = "N"+newStep;
        }

        if(sStep == 0){
            return BaseResult.error(444,"未审核，无法反审");
        }
        //当前用户不为管理员，判断该用户的审核权限
        int count = sysUserRoleService.getCountByUserNameAndRoleName(systemUserByName.getName(),"super_admin",systemUserByName.getCode());
        if(count <= 0){
            //判断该用户有无审核按钮权限
            int check = sysUserRoleService.getCountByUserName(systemUserByName.getName(),systemUserByName.getCode(),checkForm.getMenuCode());
            if(check<=0){
                return BaseResult.error(444,"没有反审核按钮权限");
            }
            //判断该用户是否有该级反审核权限
            int checkCount = sysUserRoleService.getCountByUserNameAndStep(systemUserByName.getName(),systemUserByName.getCode(),checkForm.getMenuCode(),sStep);
            if(checkCount <= 0){
                return BaseResult.error(444,"没有该级别反审核权限");
            }
            //判断是否审核金额  暂时不控制

            //判断连续两次审核能否为同一人
            if(bNoRule.getCheckUp() && sStep != 0){
                BPublicCheck bPublicCheck = this.baseMapper.getPublicCheckByRecordId(checkForm.getSheetCode(),checkForm.getRecordId(),0);
                if(bPublicCheck.getFCheckName().equals(CurrentUserUtil.getUsername())){
                    return BaseResult.error(444,"连续两次反审核不能为同一人");
                }
            }
        }
        //执行反审核
        int update;

        if(null == bNoRule.getCheckDatabase()){
            update  = bPublicCheckMapper.updateData(checkForm.getRecordId(),checkTable,newStep,checkFlag,new Date(),systemUserByName.getName());
        }else{
            update   = bPublicCheckMapper.updateCheckData(checkForm.getRecordId(),checkTable,bNoRule.getCheckDatabase(),newStep,checkFlag,new Date(),systemUserByName.getName());
        }
        if(update <= 0){
            return BaseResult.error(444,"反审核失败，修改审核状态失败");
        }

        //添加反审核记录
        BPublicCheck bPublicCheck = new BPublicCheck();
        bPublicCheck.setSheetCode(checkForm.getSheetCode());
        bPublicCheck.setCheckFlag(0);
        bPublicCheck.setRecordId(checkForm.getRecordId());
        bPublicCheck.setMenuCode(checkForm.getMenuCode());
        bPublicCheck.setFLevel(sLevel);
        bPublicCheck.setFStep(sStep);
        if(0 != sStep){
            bPublicCheck.setTimeConsuming(null);
        }else {
            BPublicCheck byRecordId = this.baseMapper.getPublicCheckByRecordId(checkForm.getSheetCode(),checkForm.getRecordId(),0);
            bPublicCheck.setTimeConsuming(this.getTimeDifference(byRecordId.getCreateTime(),new Date()));
        }
        bPublicCheck.setCheckStateFlag(checkFlag);
        bPublicCheck.setRemark("第 "+newStep+"级反审核,审核成功");
        bPublicCheck.setFCheckId(systemUserByName.getId());
        bPublicCheck.setFCheckName(systemUserByName.getName());
        bPublicCheck.setCreateTime(new Date());
        bPublicCheck.setCreateBy(systemUserByName.getName());
        boolean save = this.save(bPublicCheck);
        if(!save){
            return BaseResult.error(444,"反审核失败，添加反审核数据失败");
        }
        log.warn("反审核成功");
        return BaseResult.ok("反审核成功");
    }

    @Override
    public List<BPublicCheckVO> getList(CheckForm checkForm) {
        //获取该按钮的审核有哪些角色，哪些用户
        List<SysRoleActionCheckVO> sysRoleActionChecks = sysRoleActionCheckService.getList(checkForm);
        if(CollectionUtil.isEmpty(sysRoleActionChecks)){
            return null;
        }
        List<BPublicCheckVO> bPublicCheckVOS = new ArrayList<>();
        for (SysRoleActionCheckVO sysRoleActionCheckVO : sysRoleActionChecks) {
            List<SysRoleActionCheckVO> checkVOList = sysRoleActionCheckService.getListByCheckLevelAndMenuCode(sysRoleActionCheckVO.getCheckLevel(),checkForm.getMenuCode());
            Set<Long> roles = new HashSet<>();
            Set<String> roleNames = new HashSet<>();
            for (SysRoleActionCheckVO roleActionCheckVO : checkVOList) {
                roles.add(roleActionCheckVO.getRoleId());
                roleNames.add(roleActionCheckVO.getRoleName());
            }
            BPublicCheckVO bPublicCheckVO = new BPublicCheckVO();
            String userName = sysUserRoleService.getUserNameByRoles(roles);
            bPublicCheckVO.setUserName(userName);
            StringBuffer stringBuffer = new StringBuffer();
            for (String roleName : roleNames) {
                stringBuffer.append(roleName).append(",");
            }
            bPublicCheckVO.setRoleName(stringBuffer.substring(0,stringBuffer.length()-1));
            bPublicCheckVO.setFStep(sysRoleActionCheckVO.getCheckLevel());
            bPublicCheckVOS.add(bPublicCheckVO);
        }

        //获取最新的审核记录
        for (BPublicCheckVO bPublicCheckVO : bPublicCheckVOS) {
            BPublicCheck bPublicCheck = bPublicCheckMapper.getPublicCheckByRecordIdAndfStep(checkForm,bPublicCheckVO.getFStep());
            if(null == bPublicCheck){
                continue;
            }
            bPublicCheckVO.setCheckStateFlag(bPublicCheck.getCheckStateFlag());
            bPublicCheckVO.setFCheckName(bPublicCheck.getFCheckName());
            bPublicCheckVO.setRemark(bPublicCheck.getRemark());
            bPublicCheckVO.setTimeConsuming(bPublicCheck.getTimeConsuming());
        }

        return bPublicCheckVOS;
    }

    private String getTimeDifference(Date begin,Date end){

        //除以1000是为了转换成秒
        long between=(end.getTime()-begin.getTime())/1000;

        long day1=between/(24*3600);

        long hour1=between%(24*3600)/3600;

        long minute1=between%3600/60;

        long second1=between%60/60;

        return ""+day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒";
    }
}

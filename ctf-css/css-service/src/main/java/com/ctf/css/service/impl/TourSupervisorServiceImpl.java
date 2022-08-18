package com.ctf.css.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctf.common.result.ResultCode;
import com.ctf.css.converter.SupervisorConverter;
import com.ctf.css.mapper.TourSupervisorMapper;
import com.ctf.css.pojo.entity.SuperviseDomain;
import com.ctf.css.pojo.entity.TourSupervisor;
import com.ctf.css.pojo.entity.UserInfo;
import com.ctf.css.pojo.form.SuperviseForm;
import com.ctf.css.pojo.query.TourSupervisorPageQuery;
import com.ctf.css.pojo.vo.ex.UserVO;
import com.ctf.css.service.SuperviseDomainService;
import com.ctf.css.service.TourSupervisorService;
import com.ctf.css.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author zhangyizheng
* @description 针对表【store_tour_supervisor(巡店督导员表)】的数据库操作Service实现
* @createDate 2022-08-09 20:47:20
*/
@Service
@RequiredArgsConstructor
public class TourSupervisorServiceImpl extends ServiceImpl<TourSupervisorMapper, TourSupervisor>
    implements TourSupervisorService{

    private final UserInfoService userInfoService;
    private final SupervisorConverter supervisorConverter;
    private final SuperviseDomainService superviseDomainService;

    @Override
    public Page<UserVO> pageSupervisor(TourSupervisorPageQuery queryParams) {
        //获取分页信息
        Page<UserVO> userVOPage = new Page<>();
        userVOPage.setCurrent(queryParams.getPageNum());
        userVOPage.setSize(queryParams.getPageSize());
        // TODO: 2022/8/11 暂时无法获取部门与岗位
        //获取数据
        return this.baseMapper.getTourSupervisor(userVOPage,queryParams);
    }

    @Override
    public SuperviseForm getSupervisorFormData(String staffCode) {
        // 通过工号获取用户信息
        UserInfo userInfo = userInfoService.getBaseMapper().selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getStaffCode, staffCode));
        return supervisorConverter.userInfoToForm(userInfo);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean saveSupervisor(SuperviseForm superviseForm) {
        //2022/8/11 查看督导领域是否填写判断
        Optional.ofNullable(superviseForm.getSuperviseDomainName()).orElseThrow(() -> new RuntimeException("督导领域为空，不可新增巡店督导"));
        SuperviseDomain superviseDomain = superviseDomainService.getBaseMapper().selectOne(new LambdaQueryWrapper<SuperviseDomain>()
                .eq(SuperviseDomain::getSuperviseDomainName, superviseForm.getSuperviseDomainName()));
        //限定一个督导人员对应一个用户
        UserInfo userInfo = userInfoService.getBaseMapper().selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getStaffCode, superviseForm.getStaffCode()));
        TourSupervisor tourSupervisor = this.baseMapper.selectOne(new LambdaQueryWrapper<TourSupervisor>()
                .eq(TourSupervisor::getUserId, userInfo.getId()));
        //获取不到督导人员说明此用户未被添加，可以添加此督导人员
        if (Objects.isNull(tourSupervisor)) {
            tourSupervisor = new TourSupervisor();
            //填写督导人员信息
            tourSupervisor.setSuperviseDomainId(superviseDomain.getId());
            tourSupervisor.setUserId(userInfo.getId());
            // TODO: 2022/8/11 部门与岗位暂时无法获取，后续需添加
            return this.baseMapper.insert(tourSupervisor)>0;
        }
        //获取到了，不可添加
        throw new RuntimeException(ResultCode.PARAM_ERROR.getMsg());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSupervisor(Long supervisorId, SuperviseForm superviseForm) {
        //  2022/8/11 将表单对象转换为督导人员的用户对象
        UserInfo userInfo = supervisorConverter.formToUserInfo(superviseForm);
        // 2022/8/11 获取用户对象
        TourSupervisor tourSupervisor = this.baseMapper.selectById(supervisorId);
        userInfo.setId(tourSupervisor.getUserId());
        // TODO: 2022/8/11 部门与岗位暂时无法获取，后续添加修改
        //根据对象，修改相应信息
        return userInfoService.getBaseMapper().updateById(userInfo)>0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSupervisors(String ids) {
        String[] split = ids.split(",|，");
        List<Long> collect = Arrays.stream(split).map(Long::parseLong).collect(Collectors.toList());
        return this.baseMapper.deleteBatchIds(collect)>0;
    }

}





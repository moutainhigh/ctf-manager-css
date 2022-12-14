package com.ctf.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.ctf.admin.pojo.vo.user.UserVO;
import com.ctf.common.constant.SystemConstants;
import com.ctf.common.enums.GenderEnum;
import com.ctf.admin.listener.excel.UserImportListener;
import com.ctf.admin.converter.UserConverter;
import com.ctf.admin.dto.UserAuthDTO;
import com.ctf.admin.mapper.SysUserMapper;
import com.ctf.admin.pojo.dto.UserImportDTO;
import com.ctf.admin.pojo.entity.SysUser;
import com.ctf.admin.pojo.entity.SysUserRole;
import com.ctf.admin.pojo.form.UserForm;
import com.ctf.admin.pojo.po.UserFormPO;
import com.ctf.admin.pojo.po.UserPO;
import com.ctf.admin.pojo.query.UserPageQuery;
import com.ctf.admin.pojo.vo.user.LoginUserVO;
import com.ctf.admin.pojo.vo.user.UserExportVO;
import com.ctf.admin.service.SysPermissionService;
import com.ctf.admin.service.SysUserRoleService;
import com.ctf.admin.service.SysUserService;
import com.ctf.common.base.IBaseEnum;
import com.ctf.common.web.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ?????????????????????
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final PasswordEncoder passwordEncoder;
    private final SysUserRoleService userRoleService;
    private final UserImportListener userImportListener;
    private final SysPermissionService permissionService;
    private final UserConverter userConverter;

    /**
     * ????????????????????????
     *
     * @param queryParams
     * @return
     */
    @Override
    public IPage<UserVO> listUserPages(UserPageQuery queryParams) {

        // ????????????
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<UserPO> page = new Page<>(pageNum, pageSize);

        // ????????????
        Page<UserPO> userPoPage = this.baseMapper.listUserPages(page, queryParams);

        // ????????????
        Page<UserVO> userVoPage = userConverter.po2Vo(userPoPage);

        return userVoPage;
    }

    /**
     * ????????????????????????
     *
     * @param userId
     * @return
     */
    @Override
    public UserForm getUserFormData(Long userId) {
        UserFormPO userFormPO = this.baseMapper.getUserFormData(userId);
        // ????????????po->form
        UserForm userForm = userConverter.po2Form(userFormPO);
        return userForm;
    }

    /**
     * ????????????
     *
     * @param userForm ??????????????????
     * @return
     */
    @Override
    public boolean saveUser(UserForm userForm) {

        // ???????????? form->entity
        SysUser entity = userConverter.form2Entity(userForm);

        // ????????????????????????
        String defaultEncryptPwd = passwordEncoder.encode(SystemConstants.DEFAULT_USER_PASSWORD);
        entity.setPassword(defaultEncryptPwd);

        // ????????????
        boolean result = this.save(entity);

        if (result) {
            // ??????????????????????????????
            Long userId = entity.getId();
            List<Long> roleIds = userForm.getRoleIds();
            if (CollectionUtil.isNotEmpty(roleIds)) {
                List<SysUserRole> userRoles = roleIds
                        .stream()
                        .map(roleId -> new SysUserRole(userId, roleId))
                        .collect(Collectors.toList());
                userRoleService.saveBatch(userRoles);
            }
        }
        return result;
    }

    /**
     * ????????????
     *
     * @param userId   ??????ID
     * @param userForm ??????????????????
     * @return
     */
    @Override
    @Transactional
    public boolean updateUser(Long userId, UserForm userForm) {
        // ??????????????????ID??????
        List<Long> oldRoleIds = userRoleService.list(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, userId))
                .stream()
                .map(item -> item.getRoleId())
                .collect(Collectors.toList());

        // ??????????????????ID??????
        List<Long> newRoleIds = userForm.getRoleIds();

        // ?????????????????????
        List<Long> addRoleIds = newRoleIds.stream().filter(roleId -> !oldRoleIds.contains(roleId)).collect(Collectors.toList());
        List<SysUserRole> addUserRoles = Optional.ofNullable(addRoleIds).orElse(new ArrayList<>())
                .stream().map(roleId -> new SysUserRole(userId, roleId))
                .collect(Collectors.toList());
        userRoleService.saveBatch(addUserRoles);

        // ?????????????????????
        List<Long> removeRoleIds = oldRoleIds.stream().filter(roleId -> !newRoleIds.contains(roleId)).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(removeRoleIds)) {
            userRoleService.remove(new LambdaQueryWrapper<SysUserRole>()
                    .eq(SysUserRole::getUserId, userId)
                    .in(SysUserRole::getRoleId, removeRoleIds)
            );
        }


        // form -> entity
        SysUser entity = userConverter.form2Entity(userForm);

        // ????????????
        boolean result = this.updateById(entity);
        return result;
    }

    /**
     * ????????????
     *
     * @param idsStr ??????ID????????????????????????(,)??????
     * @return
     */
    @Override
    public boolean deleteUsers(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "???????????????????????????");
        // ????????????
        List<Long> ids = Arrays.asList(idsStr.split(",")).stream()
                .map(idStr -> Long.parseLong(idStr)).collect(Collectors.toList());
        boolean result = this.removeByIds(ids);
        return result;

    }

    /**
     * ??????????????????
     *
     * @param userId   ??????ID
     * @param password ????????????
     * @return
     */
    @Override
    public boolean updateUserPassword(Long userId, String password) {
        String encryptedPassword = passwordEncoder.encode(password);
        boolean result = this.update(new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, userId)
                .set(SysUser::getPassword, encryptedPassword)
        );

        return result;
    }

    /**
     * ?????????????????????????????????
     *
     * @param username
     * @return
     */
    @Override
    public UserAuthDTO getAuthInfoByUsername(String username) {
        UserAuthDTO userAuthInfo = this.baseMapper.getAuthInfoByUsername(username);
        return userAuthInfo;
    }

    /**
     * ????????????
     *
     * @param userImportDTO
     * @return
     */
    @Transactional
    @Override
    public String importUsers(UserImportDTO userImportDTO) throws IOException {

        Long deptId = userImportDTO.getDeptId();
        List<Long> roleIds = Arrays.stream(userImportDTO.getRoleIds().split(",")).map(roleId -> Convert.toLong(roleId)).collect(Collectors.toList());
        InputStream inputStream = userImportDTO.getFile().getInputStream();

        ExcelReaderBuilder excelReaderBuilder = EasyExcel.read(inputStream, UserImportDTO.UserItem.class, userImportListener);
        ExcelReaderSheetBuilder sheet = excelReaderBuilder.sheet();
        List<UserImportDTO.UserItem> list = sheet.doReadSync();

        Assert.isTrue(CollectionUtil.isNotEmpty(list), "????????????????????????");

        // ??????????????????
        List<UserImportDTO.UserItem> validDataList = list.stream()
                .filter(item -> StrUtil.isNotBlank(item.getUsername()))
                .collect(Collectors.toList());

        Assert.isTrue(CollectionUtil.isNotEmpty(validDataList), "????????????????????????");

        long distinctCount = validDataList.stream()
                .map(UserImportDTO.UserItem::getUsername)
                .distinct()
                .count();
        Assert.isTrue(validDataList.size() == distinctCount, "???????????????????????????????????????????????????");

        List<SysUser> saveUserList = Lists.newArrayList();

        StringBuilder errMsg = new StringBuilder();
        for (int i = 0; i < validDataList.size(); i++) {
            UserImportDTO.UserItem userItem = validDataList.get(i);

            String username = userItem.getUsername();
            if (StrUtil.isBlank(username)) {
                errMsg.append(StrUtil.format("???{}????????????????????????????????????????????????", i + 1));
                continue;
            }

            String nickname = userItem.getNickname();
            if (StrUtil.isBlank(nickname)) {
                errMsg.append(StrUtil.format("???{}???????????????????????????????????????????????????", i + 1));
                continue;
            }

            SysUser user = new SysUser();
            user.setUsername(username);
            user.setNickname(nickname);
            user.setMobile(userItem.getMobile());
            user.setEmail(userItem.getEmail());
            user.setDeptId(deptId);
            // ????????????
            user.setPassword(passwordEncoder.encode(SystemConstants.DEFAULT_USER_PASSWORD));
            // ????????????
            Integer gender = (Integer) IBaseEnum.getValueByLabel(userItem.getGender(), GenderEnum.class);
            user.setGender(gender);

            saveUserList.add(user);
        }

        if (CollectionUtil.isNotEmpty(saveUserList)) {
            boolean result = this.saveBatch(saveUserList);
            Assert.isTrue(result, "????????????????????????????????????????????????");

            List<SysUserRole> userRoleList = new ArrayList<>();

            if (CollectionUtil.isNotEmpty(roleIds)) {

                roleIds.forEach(roleId -> {
                    userRoleList.addAll(
                            saveUserList.stream()
                                    .map(user -> new SysUserRole(user.getId(), roleId)).
                                    collect(Collectors.toList()));
                });
            }

            userRoleService.saveBatch(userRoleList);
        }

        errMsg.append(StrUtil.format("??????{}????????????????????????{}??????????????????????????????{}???", list.size(), saveUserList.size(), list.size() - saveUserList.size()));
        return errMsg.toString();

    }

    /**
     * ????????????????????????
     *
     * @param queryParams
     * @return
     */
    @Override
    public List<UserExportVO> listExportUsers(UserPageQuery queryParams) {
        List<UserExportVO> list = this.baseMapper.listExportUsers(queryParams);
        return list;
    }

    /**
     * ????????????????????????
     *
     * @return
     */
    @Override
    public LoginUserVO getLoginUserInfo() {
        // ????????????entity
        SysUser user = this.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getId, UserUtils.getUserId())
                .select(
                        SysUser::getId,
                        SysUser::getNickname,
                        SysUser::getAvatar
                )
        );
        // entity->VO
        LoginUserVO loginUserVO = userConverter.entity2LoginUser(user);

        // ??????????????????
        List<String> roles = UserUtils.getRoles();
        loginUserVO.setRoles(roles);

        // ????????????????????????
        List<String> perms = permissionService.listBtnPermByRoles(roles);
        loginUserVO.setPerms(perms);

        return loginUserVO;
    }

}

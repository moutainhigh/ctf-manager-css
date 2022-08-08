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
 * 用户业务实现类
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
     * 获取用户分页列表
     *
     * @param queryParams
     * @return
     */
    @Override
    public IPage<UserVO> listUserPages(UserPageQuery queryParams) {

        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<UserPO> page = new Page<>(pageNum, pageSize);

        // 查询数据
        Page<UserPO> userPoPage = this.baseMapper.listUserPages(page, queryParams);

        // 实体转换
        Page<UserVO> userVoPage = userConverter.po2Vo(userPoPage);

        return userVoPage;
    }

    /**
     * 获取用户表单详情
     *
     * @param userId
     * @return
     */
    @Override
    public UserForm getUserFormData(Long userId) {
        UserFormPO userFormPO = this.baseMapper.getUserFormData(userId);
        // 实体转换po->form
        UserForm userForm = userConverter.po2Form(userFormPO);
        return userForm;
    }

    /**
     * 新增用户
     *
     * @param userForm 用户表单对象
     * @return
     */
    @Override
    public boolean saveUser(UserForm userForm) {

        // 实体转换 form->entity
        SysUser entity = userConverter.form2Entity(userForm);

        // 设置默认加密密码
        String defaultEncryptPwd = passwordEncoder.encode(SystemConstants.DEFAULT_USER_PASSWORD);
        entity.setPassword(defaultEncryptPwd);

        // 新增用户
        boolean result = this.save(entity);

        if (result) {
            // 保存用户角色关联信息
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
     * 更新用户
     *
     * @param userId   用户ID
     * @param userForm 用户表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean updateUser(Long userId, UserForm userForm) {
        // 用户的旧角色ID集合
        List<Long> oldRoleIds = userRoleService.list(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, userId))
                .stream()
                .map(item -> item.getRoleId())
                .collect(Collectors.toList());

        // 用户的新角色ID集合
        List<Long> newRoleIds = userForm.getRoleIds();

        // 新增用户的角色
        List<Long> addRoleIds = newRoleIds.stream().filter(roleId -> !oldRoleIds.contains(roleId)).collect(Collectors.toList());
        List<SysUserRole> addUserRoles = Optional.ofNullable(addRoleIds).orElse(new ArrayList<>())
                .stream().map(roleId -> new SysUserRole(userId, roleId))
                .collect(Collectors.toList());
        userRoleService.saveBatch(addUserRoles);

        // 删除用户的角色
        List<Long> removeRoleIds = oldRoleIds.stream().filter(roleId -> !newRoleIds.contains(roleId)).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(removeRoleIds)) {
            userRoleService.remove(new LambdaQueryWrapper<SysUserRole>()
                    .eq(SysUserRole::getUserId, userId)
                    .in(SysUserRole::getRoleId, removeRoleIds)
            );
        }


        // form -> entity
        SysUser entity = userConverter.form2Entity(userForm);

        // 修改用户
        boolean result = this.updateById(entity);
        return result;
    }

    /**
     * 删除用户
     *
     * @param idsStr 用户ID，多个以英文逗号(,)分割
     * @return
     */
    @Override
    public boolean deleteUsers(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的用户数据为空");
        // 逻辑删除
        List<Long> ids = Arrays.asList(idsStr.split(",")).stream()
                .map(idStr -> Long.parseLong(idStr)).collect(Collectors.toList());
        boolean result = this.removeByIds(ids);
        return result;

    }

    /**
     * 修改用户密码
     *
     * @param userId   用户ID
     * @param password 用户密码
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
     * 根据用户名获取认证信息
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
     * 导入用户
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

        Assert.isTrue(CollectionUtil.isNotEmpty(list), "未检测到任何数据");

        // 有效数据集合
        List<UserImportDTO.UserItem> validDataList = list.stream()
                .filter(item -> StrUtil.isNotBlank(item.getUsername()))
                .collect(Collectors.toList());

        Assert.isTrue(CollectionUtil.isNotEmpty(validDataList), "未检测到有效数据");

        long distinctCount = validDataList.stream()
                .map(UserImportDTO.UserItem::getUsername)
                .distinct()
                .count();
        Assert.isTrue(validDataList.size() == distinctCount, "导入数据中有重复的用户名，请检查！");

        List<SysUser> saveUserList = Lists.newArrayList();

        StringBuilder errMsg = new StringBuilder();
        for (int i = 0; i < validDataList.size(); i++) {
            UserImportDTO.UserItem userItem = validDataList.get(i);

            String username = userItem.getUsername();
            if (StrUtil.isBlank(username)) {
                errMsg.append(StrUtil.format("第{}条数据导入失败，原因：用户名为空", i + 1));
                continue;
            }

            String nickname = userItem.getNickname();
            if (StrUtil.isBlank(nickname)) {
                errMsg.append(StrUtil.format("第{}条数据导入失败，原因：用户昵称为空", i + 1));
                continue;
            }

            SysUser user = new SysUser();
            user.setUsername(username);
            user.setNickname(nickname);
            user.setMobile(userItem.getMobile());
            user.setEmail(userItem.getEmail());
            user.setDeptId(deptId);
            // 默认密码
            user.setPassword(passwordEncoder.encode(SystemConstants.DEFAULT_USER_PASSWORD));
            // 性别转换
            Integer gender = (Integer) IBaseEnum.getValueByLabel(userItem.getGender(), GenderEnum.class);
            user.setGender(gender);

            saveUserList.add(user);
        }

        if (CollectionUtil.isNotEmpty(saveUserList)) {
            boolean result = this.saveBatch(saveUserList);
            Assert.isTrue(result, "导入数据失败，原因：保存用户出错");

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

        errMsg.append(StrUtil.format("一共{}条数据，成功导入{}条数据，导入失败数据{}条", list.size(), saveUserList.size(), list.size() - saveUserList.size()));
        return errMsg.toString();

    }

    /**
     * 获取导出用户列表
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
     * 获取登录用户信息
     *
     * @return
     */
    @Override
    public LoginUserVO getLoginUserInfo() {
        // 登录用户entity
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

        // 用户角色集合
        List<String> roles = UserUtils.getRoles();
        loginUserVO.setRoles(roles);

        // 用户按钮权限集合
        List<String> perms = permissionService.listBtnPermByRoles(roles);
        loginUserVO.setPerms(perms);

        return loginUserVO;
    }

}

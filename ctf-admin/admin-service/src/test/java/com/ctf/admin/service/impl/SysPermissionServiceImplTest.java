package com.ctf.admin.service.impl;

import com.ctf.admin.mapper.SysPermissionMapper;
import com.ctf.admin.pojo.entity.SysPermission;
import com.ctf.admin.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author H.m
 * @description TODO
 * @createTime 2021/5/30 23:19
 */
@SpringBootTest
@Slf4j
class SysPermissionServiceImplTest {

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    SysPermissionService sysPermissionService;

    @Test
    void listPermissionRoles() {
        List<SysPermission> sysPermissions = sysPermissionMapper.listPermRoles();
        log.info(sysPermissions.toString());
    }

    @Test
    void refreshPermRolesRules(){

    }
}

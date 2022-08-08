package com.ctf.admin.component;

import com.ctf.admin.service.SysPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * 容器启动完成时加载角色权限规则至Redis缓存
 *
 * @ClassName InitPermissionRolesCache
 * @Description
 * @Author H.m
 * @date 2022/8/5 10:30
 * @Version 1.0
 **/
@Component
@RequiredArgsConstructor
public class InitPermissionRolesCache implements CommandLineRunner {

    private final SysPermissionService sysPermissionService;

    @Override
    public void run(String... args) {
        sysPermissionService.refreshPermRolesRules();
    }
}

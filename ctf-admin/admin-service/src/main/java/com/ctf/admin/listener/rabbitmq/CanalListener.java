package com.ctf.admin.listener.rabbitmq;

import com.ctf.admin.service.SysMenuService;
import com.ctf.admin.service.SysOauthClientService;
import com.ctf.admin.service.SysPermissionService;
import com.ctf.common.web.domain.CanalMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Canal + RabbitMQ 监听数据库数据变化
 *
 * @ClassName CanalListener
 * @Description
 * @Author H.m
 * @date 2022/8/5 10:30
 * @Version 1.0
 **/

@Component
@Slf4j
@RequiredArgsConstructor
public class CanalListener {

    private final SysPermissionService permissionService;
    private final SysOauthClientService oauthClientService;
    private final SysMenuService menuService;

    @RabbitListener(queues = "canal.queue")
    public void handleDataChange(@Payload CanalMessage message) {
        String tableName = message.getTable();

        log.info("Canal 监听 {} 发生变化；明细：{}", tableName, message);

        if ("sys_oauth_client".equals(tableName)) {
            log.info("======== 清除客户端信息缓存 ========");
            oauthClientService.cleanCache();
        } else if (Arrays.asList("sys_permission", "sys_role", "sys_role_permission").contains(tableName)) {
            log.info("======== 刷新角色权限缓存 ========");
            permissionService.refreshPermRolesRules();
        } else if (Arrays.asList("sys_menu", "sys_role", "sys_role_menu").contains(tableName)) {
            log.info("======== 清理菜单路由缓存 ========");
            menuService.cleanCache();
        }
    }
}

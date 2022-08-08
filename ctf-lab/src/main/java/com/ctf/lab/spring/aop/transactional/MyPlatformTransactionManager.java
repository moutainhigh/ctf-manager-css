package com.ctf.lab.spring.aop.transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 事务管理器
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
//@Configuration
public class MyPlatformTransactionManager {
    // 使用DataSourceTransactionManager来管理事务
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;
    }
}

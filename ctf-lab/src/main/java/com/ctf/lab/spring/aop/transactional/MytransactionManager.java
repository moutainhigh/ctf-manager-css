package com.ctf.lab.spring.aop.transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 开启事务支持
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
//@EnableTransactionManagement
public class MytransactionManager {
    // 使用DataSourceTransactionManager来管理事务
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;
    }
}

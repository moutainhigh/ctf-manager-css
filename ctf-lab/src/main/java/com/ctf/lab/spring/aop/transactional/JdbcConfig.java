package com.ctf.lab.spring.aop.transactional;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 *  数据库连接配置
 *
 * @author H.m
 * @date 2022/8/5 10:30
 */
//@Configuration
public class JdbcConfig {
    // 此处只是为了演示 所以不用连接池了===========生产环境禁止这么使用==========
    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setURL("jdbc:mysql://localhost:3306/mall_ums?serverTimezone=UTC");
        return dataSource;
    }

    //// 为了执行sql方便 此处采用JdbcTemplate进行===========
    // 生产环境一下一般我们不需要此配置，因为一般我们会使用ORM框架~
    // 但是如果是SpringBoot，这两个类默认都会配置上（导入了Spring-JDBC的jar即可）  比如MyBatis就是基于JDBC的
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}

package com.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@ComponentScan(basePackages = "com.dao")

public class TrConfig {
    @Autowired
    DataSource dataSource;

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource);
    }



}

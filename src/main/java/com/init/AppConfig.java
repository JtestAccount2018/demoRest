package com.init;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean
    DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder =  DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:test");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
       return dataSourceBuilder.build();
    }

  /*  @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder().generateUniqueName(false).setName("testdb").
                setType(EmbeddedDatabaseType.H2).addDefaultScripts().setScriptEncoding("UTF-8").
                ignoreFailedDrops(true).build();
    }*/
@Bean
NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource());
}

}

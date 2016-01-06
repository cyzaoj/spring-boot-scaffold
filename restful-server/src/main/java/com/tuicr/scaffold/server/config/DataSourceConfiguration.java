package com.tuicr.scaffold.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.tuicr.scaffold.server.properties.DataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

/**
 * @author ylxia
 * @version 1.0
 * @package com.iyerka.config
 * @date 九月
 */

@Slf4j
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceConfiguration {

    /**
     * 初始化数据源
     *
     * @return
     * @throws SQLException
     */
    @Primary
    @Bean(name = "primaryDataSource", destroyMethod = "close")
    public DruidDataSource druidDataSource(DataSourceProperties dataSourceProperties) throws SQLException {
        DruidDataSource source = new DruidDataSource();
        source.setUsername(dataSourceProperties.getUserName());
        source.setPassword(dataSourceProperties.getPassWord());
        source.setUrl(dataSourceProperties.getUrl());
        source.setDriverClassName(dataSourceProperties.getDriverClassName());
        source.setFilters(dataSourceProperties.getFilters());
        source.setMaxActive(dataSourceProperties.getMaxActive());
        source.setInitialSize(dataSourceProperties.getInitialSize());
        source.setMinIdle(dataSourceProperties.getMinIdle());
        source.setMaxWait(dataSourceProperties.getMaxWait());
        source.setTimeBetweenEvictionRunsMillis(dataSourceProperties.getTimeBetweenEvictionRunsMillis());
        source.setMinEvictableIdleTimeMillis(dataSourceProperties.getMinEvictableIdleTimeMillis());
        source.setValidationQuery(dataSourceProperties.getValidationQuery());
        source.setTestWhileIdle(dataSourceProperties.isTestWhileIdle());
        source.setTestOnReturn(dataSourceProperties.isTestOnReturn());
        source.setTestOnBorrow(dataSourceProperties.isTestOnBorrow());
        source.setMaxOpenPreparedStatements(dataSourceProperties.getMaxOpenPreparedStatements());
        source.setRemoveAbandoned(dataSourceProperties.isRemoveAbandoned());
        source.setRemoveAbandonedTimeout(dataSourceProperties.getRemoveAbandonedTimeout());
        source.setLogAbandoned(dataSourceProperties.isLogAbandoned());
        return source;
    }

}



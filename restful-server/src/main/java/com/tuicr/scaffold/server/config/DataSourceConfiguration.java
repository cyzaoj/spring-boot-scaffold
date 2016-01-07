package com.tuicr.scaffold.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.tuicr.scaffold.server.properties.DataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
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

    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setProxyTargetClass(true);
        creator.setBeanNames("*Service");
        creator.setInterceptorNames("druidStatInterceptor");
        return creator;
    }


    @Bean(name = "druidStatInterceptor")
    public DruidStatInterceptor druidStatInterceptor() {
        DruidStatInterceptor interceptor = new DruidStatInterceptor();
        return interceptor;
    }

    /**
     * 添加druid数据源监控
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatView() {
        printStacks();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setName("druidStatView");
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        return servletRegistrationBean;
    }


    @Bean
    public FilterRegistrationBean druidWebStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        WebStatFilter webStatFilter = new WebStatFilter();
        filterRegistrationBean.setFilter(webStatFilter);
        filterRegistrationBean.addInitParameter(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid*,*.png,*.jpg,*.gif");
        filterRegistrationBean.addInitParameter(WebStatFilter.PARAM_NAME_SESSION_STAT_MAX_COUNT, "1000");
        filterRegistrationBean.addInitParameter(WebStatFilter.PARAM_NAME_PROFILE_ENABLE, Boolean.TRUE.toString());
        filterRegistrationBean.addUrlPatterns("/");
        return filterRegistrationBean;
    }

    private void printStacks() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        log.debug("========================");

        for (int i = 0; i < elements.length; i++) {
            log.debug(ToStringBuilder.reflectionToString(elements[i]));
        }
    }


}



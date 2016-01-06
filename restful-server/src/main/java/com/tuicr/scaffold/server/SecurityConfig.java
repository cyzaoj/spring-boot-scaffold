package com.tuicr.scaffold.server;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Properties;

/**
 * spring security安全认证集成
 *
 * @author ylxia
 * @version 1.0
 * @package com.tuicr.weibo.server.config
 * @date 15/12/17
 */

@Slf4j
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(MvcConfig.IGNORE_RESOURCES);
    }


    /**
     * 内存文件
     * @return
     */
    public UserDetailsService userDetailsService() {
        Properties properties = new Properties();
        properties.put("admin", "admin!#123,enabled,ROLE_ADMIN");
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(properties);
        return inMemoryUserDetailsManager;
    }


    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {


        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                anyRequest().authenticated().and().
                httpBasic().and().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                csrf().disable();
    }


}

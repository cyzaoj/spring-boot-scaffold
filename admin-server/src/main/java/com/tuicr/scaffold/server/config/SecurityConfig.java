package com.tuicr.scaffold.server.config;

import com.tuicr.scaffold.server.properties.Secure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author ylxia
 * @version 1.0
 * @package com.tuicr.weibo.server.config
 * @date 15/12/17
 */

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties({
        Secure.class,
})
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private Secure secure;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(MvcConfig.IGNORE_RESOURCES);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .formLogin()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginPage(secure.getLoginPage())
                    .loginProcessingUrl(secure.getLoginProcessingUrl())
                    .failureUrl(secure.getLoginPage() + "?param.error=bad_credentials")
                .and()
                    .csrf()
                .and()
                    .addFilter(new UsernamePasswordAuthenticationFilter())
                    .logout()
                    .logoutUrl(secure.getLogoutUrl())
                    .deleteCookies("JSESSIONID")
                .and()
                    .authorizeRequests()
                    .antMatchers(MvcConfig.IGNORE_URIS).permitAll()
                    .anyRequest().authenticated()
                .and()
                .rememberMe()
                .and()
                    .exceptionHandling().accessDeniedPage("/error?param.error=denied")
                .and()
                    .csrf()
                    .ignoringAntMatchers("/api/**");
    }

    @Autowired
    public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
       // initAuthProvider(accountDetailsService, auth);
    }


   /* *//**
     * 初始化
     *
     * @param accountDetailsService
     * @param auth
     *//*
    void initAuthProvider(AccountDetailsService accountDetailsService,
                          AuthenticationManagerBuilder auth) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new Md5PasswordEncoder());
        provider.setSaltSource(user -> user.getUsername());
        provider.setUserDetailsService(accountDetailsService);
        auth.authenticationProvider(provider);
    }*/

}

package com.tuicr.scaffold.server;


import com.tuicr.scaffold.server.properties.Secure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
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


    public final static String EXPIRE_URI_PARAM = "?param.error=expired";

    public final static  String CREDENTIALS_URI_PARAM = "?param.error=bad_credentials";

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
                .authorizeRequests()
                    .antMatchers(MvcConfig.IGNORE_URIS).permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage(secure.getLoginPage())
                        .loginProcessingUrl(secure.getLoginProcessingUrl())
                        .defaultSuccessUrl(secure.getLoginSuccessUrl())
                        .failureUrl(secure.getLoginPage() + CREDENTIALS_URI_PARAM)
                .and()
                    .csrf()
                .and()
                    .addFilterBefore(captchaFilter(),UsernamePasswordAuthenticationFilter.class)
                .logout()
                    .logoutUrl(secure.getLogoutUrl())
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl(secure.getLoginPage())
                .and()
                    .rememberMe()
                .and()
                    .exceptionHandling().accessDeniedPage("/error?param.error=denied")
                .and()
                    .sessionManagement()
                    .maximumSessions(1)
                    .expiredUrl(secure.getLoginPage() + EXPIRE_URI_PARAM)
                .and()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .invalidSessionUrl(secure.getLoginPage() + EXPIRE_URI_PARAM);
    }


    /**
     * 自定义验证码
     *
     * @return
     * @throws Exception
     */
    public AbstractAuthenticationProcessingFilter captchaFilter() throws Exception {
        CaptchaAuthenticationProcessingFilter captchaAuthenticationProcessingFilter =  new CaptchaAuthenticationProcessingFilter(false);
        captchaAuthenticationProcessingFilter.setAuthenticationManager(authenticationManagerBean());
        return captchaAuthenticationProcessingFilter;
    }

    @Autowired
    public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin!#123")
                .roles(secure.getAdminRole());
    }
}

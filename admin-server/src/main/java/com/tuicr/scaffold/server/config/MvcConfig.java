package com.tuicr.scaffold.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ylxia
 * @version 1.0
 * @package com.iyerka.server.config
 * @date 九月
 */
@Configuration
//@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 不需要拦截处理的URI
     */
    public static final String[] IGNORE_URIS = {
            "/social/**",
            "/error/**",
            "/kaptcha/**",
            "/auth/**",
            "/login",
            "/signin/**",
            "/signup/**",
            "/authorzie/**",
            "/api/**"
    };


    /**
     * 过滤资源
     */
    public static final String[] IGNORE_RESOURCES = {
            "/bower_components/**",
            "/**/*.css",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.jpg"
    };

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        //registry.addViewController("/login").setViewName("/authorzie/login");
        //registry.addViewController("/logout").setViewName("/auth/logout");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(IGNORE_RESOURCES)
                .addResourceLocations("/bower_components/")
                .setCachePeriod(31556926);
    }

}
package com.tuicr.scaffold;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Locale;

/**
 * 主服务入口
 *
 * @author ylxia
 * @version 1.0
 * @package com.chatterbot
 * @date 2015-08-21 15:12:35
 */

@SpringBootApplication
@PropertySources({

        //默认配置
        @PropertySource("classpath:dbconfig.properties"),
        @PropertySource("classpath:dubbo.properties"),
        @PropertySource("classpath:secure.properties"),


        //tomcat目录下
        @PropertySource(value = "file:${CATALINA_BASE}/conf/dbconfig.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:${CATALINA_BASE}/conf/dubbo.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:${CATALINA_BASE}/conf/secure.properties", ignoreResourceNotFound = true),

        //${user.dir} 运行在项目同目录下
        @PropertySource(value = "file:${user.dir}/conf/dbconfig.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:${user.dir}/conf/dubbo.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:${user.dir}/conf/secure.properties", ignoreResourceNotFound = true)
})
@Slf4j
@EnableAspectJAutoProxy
@EnableScheduling
@ComponentScan(basePackages = {"dubbo", "com.tuicr"})
public class Bootstrap extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Bootstrap.class);
    }

    /**
     * spring boot 服务主入口
     *
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Bootstrap.class, args);
        if (context instanceof EmbeddedWebApplicationContext) {
            int port = ((EmbeddedWebApplicationContext) context).getEmbeddedServletContainer().getPort();
            String contextPath = context.getApplicationName();
            String url = String.format(Locale.SIMPLIFIED_CHINESE, "http://localhost:%d%s", port, contextPath);

            //提示项目用到的相关配置文件
            log.info(" =========== ${user.dir}={} ===========  ", System.getProperty("user.dir"));
            log.info(" =========== ${java.io.tmpdir}={} ===========  ", System.getProperty("java.io.tmpdir"));

            String dashes = "------------------------------------------------------------------------";
            log.info("Access URLs:\n{}\n\tLocal: \t\t{}\n{}", dashes, url, dashes);
        }
    }

}
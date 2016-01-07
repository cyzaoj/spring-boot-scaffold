package com.tuicr.scaffold.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ylxia
 * @version 1.0
 * @package com.tuicr.scaffold.config
 * @date 九月
 */
@Data
@ConfigurationProperties(prefix = "jdbc", ignoreUnknownFields = false)
//@PropertySource("classpath:dbconfig.properties")
public class DataSourceProperties {


    //@Value("${jdbc.url}")
    private String url;

    //@Value("${jdbc.username}")
    private String userName;

    //@Value("${jdbc.password}")
    private String passWord;

    //@Value("${jdbc.driverClassName}")
    private String driverClassName;

    //@Value("${jdbc.filters}")
    private String filters;


    //@Value("${jdbc.maxActive}")
    private int maxActive;

    //@Value("${jdbc.initialSize}")
    private int initialSize;

    //@Value("${jdbc.maxWait}")
    private int maxWait;

    //@Value("${jdbc.minIdle}")
    private int minIdle;

    //@Value("${jdbc.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    //@Value("${jdbc.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    //@Value("${jdbc.validationQuery}")
    private String validationQuery;

    //@Value("${jdbc.testWhileIdle}")
    private boolean testWhileIdle;

    //@Value("${jdbc.testOnBorrow}")
    private boolean testOnBorrow;

    //@Value("${jdbc.testOnReturn}")
    private boolean testOnReturn;

    //@Value("${jdbc.maxOpenPreparedStatements}")
    private int maxOpenPreparedStatements;

    //@Value("${jdbc.removeAbandoned}")
    private boolean removeAbandoned;

    //@Value("${jdbc.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;

    //@Value("${jdbc.logAbandoned}")
    private boolean logAbandoned;


}
package com.tuicr.scaffold.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 */
@Data
@ConfigurationProperties(prefix = "dubbo.registry")
public class DubboRegistry {

    /**
     * 接口协议
     */
    private String protocol = "zookeeper";

    /**
     * 注册中心地址
     */
    private String address = "127.0.0.1:2181";

    /**
     * 是否向注册中心注册服务
     */
    private boolean register = true;

    /**
     * 是否向注册中心订阅服务
     */
    private boolean subscribe = true;
}

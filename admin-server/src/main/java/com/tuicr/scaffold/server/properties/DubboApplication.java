package com.tuicr.scaffold.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 */
@Data
@ConfigurationProperties(prefix = "dubbo.application")
public class DubboApplication {

    private String name = "sample";

    private String logger = "slf4j";
}

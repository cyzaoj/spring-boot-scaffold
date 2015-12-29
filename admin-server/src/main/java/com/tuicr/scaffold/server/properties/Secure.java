package com.tuicr.scaffold.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ylxia
 * @version 1.0
 * @package com.tuicr.weibo.server.properties
 * @date 15/12/20
 */

@Data
@ConfigurationProperties(prefix = "secure")
public class Secure {


    /**
     * 权限分组名称
     */
    private String role;

    /**
     * 登录页面地址
     */
    private String loginPage;

    /**
     * 成功跳转地址
     */
    private String loginSuccessUrl = "/";

    /**
     * 处理登录请求地址
     */
    private String loginProcessingUrl = "login";


    /**
     * 登录跳转
     */
    private String oauthLoginProcessingUrl = "/auth";

    /**
     * 登出地址
     */
    private String logoutUrl = "logout";
}
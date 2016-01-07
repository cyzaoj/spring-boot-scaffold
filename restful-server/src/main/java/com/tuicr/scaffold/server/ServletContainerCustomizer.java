package com.tuicr.scaffold.server;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author ylxia
 * @version 1.0
 * @package com.tuicr.scaffold.server
 * @date 15/12/29
 */
@Component
public class ServletContainerCustomizer implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.addErrorPages(
                new ErrorPage(HttpStatus.BAD_REQUEST, "/error/notfound"),
                new ErrorPage(HttpStatus.NOT_FOUND, "/error/notfound")
        );
        container.addInitializers();
    }
}
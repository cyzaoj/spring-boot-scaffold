package com.tuicr.scaffold.controller;

import com.tuicr.scaffold.data.DataModelResult;
import com.tuicr.scaffold.data.StateCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * restful接口异常处理
 *
 * @author ylxia
 * @version 1.0
 * @package com.tuicr.authcenter.controller
 * @date 15/12/16
 */
@Slf4j
@ControllerAdvice(annotations = {
        Controller.class
})
public class ViewResolverAdvice {


    /**
     * Handle exceptions thrown by handlers.
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String exception(Exception exception, WebRequest request) {
        log.error("RestfulApiAdvice -> [ params={} exception={} message={}] ", request.getParameterMap(), exception.getClass(), exception.getMessage());

        return "";
    }
}
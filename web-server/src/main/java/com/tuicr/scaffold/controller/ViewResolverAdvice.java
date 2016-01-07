package com.tuicr.scaffold.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

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
package com.tuicr.scaffold.controller;

import com.tuicr.scaffold.server.data.DataModelResult;
import com.tuicr.scaffold.server.data.StateCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
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
        ResponseBody.class,
        RestController.class
})
public class RestfulApiAdvice {


    /**
     * Handle exceptions thrown by handlers.
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public DataModelResult<String> exception(Exception exception, WebRequest request) {
        log.error("RestfulApiAdvice -> [ params={} exception={} message={}] ", request.getParameterMap(), exception.getClass(), exception.getMessage());
        String message = StringUtils.EMPTY;
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        WebApplicationContext ac = RequestContextUtils.findWebApplicationContext(httpServletRequest);

        int stateCode = StateCode.ERROR;
        if (exception instanceof SQLException) {
            stateCode = StateCode.ERROR_DB;
        } else if (exception instanceof DataIntegrityViolationException) {
            stateCode = StateCode.ERRORDB_UNIQUE;
            message = ac.getMessage("common.error.unique", null, request.getLocale());
        } else if (exception instanceof AuthenticationException) {
            stateCode = StateCode.ERROR_ACCOUNT;
            message = ac.getMessage("account.error.auth", null, request.getLocale());
        } else {
            message = StringUtils.isBlank(exception.getMessage()) ? exception.getClass().getName() : exception.getMessage();
        }
        return new DataModelResult(stateCode, null, message);
    }
}
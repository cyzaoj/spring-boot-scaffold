package com.tuicr.scaffold.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ylxia
 * @version 1.0
 * @package com.tuicr.authcenter.server
 * @date 15/12/29
 */
@Slf4j
public class CaptchaAuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter {


    public CaptchaAuthenticationProcessingFilter() {
        super();
    }

    private String captchaParameter = "captcha";
    private boolean state = Boolean.TRUE;


    public CaptchaAuthenticationProcessingFilter(boolean state) {
        this.state = state;
    }

    protected String obtainCaptcha(HttpServletRequest request) {
        return request.getParameter(captchaParameter);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        String captcha = this.obtainCaptcha(request);

        if (StringUtils.isBlank(captcha) && state) {
            log.error("Captcha is Invalid,params={}", ToStringBuilder.reflectionToString(request.getParameterMap()));
            throw new BadCredentialsException("Captcha is Invalid !");
        }
        return super.attemptAuthentication(request, response);
    }
}
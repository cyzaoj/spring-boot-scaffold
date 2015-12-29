package com.tuicr.scaffold.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ylxia
 * @version 1.0
 * @package com.tuicr.authcenter.controller
 * @date 15/12/29
 */
@Slf4j
@Controller
@RequestMapping("auth")
public class AuthController {


    /**
     * @return
     */
    @RequestMapping(value = "loginPage", method = RequestMethod.GET)
    public String loginPage() {
        return "/auth/login";
    }


}
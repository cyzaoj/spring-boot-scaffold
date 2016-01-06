package com.tuicr.scaffold.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ylxia
 * @version 1.0
 * @package com.tuicr.scaffold.controller.api
 * @date 16/1/6
 */
@Slf4j
@RestController
@RequestMapping("helloworld")
public class HelloWorldController {

    /**
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loginPage() {
        return "helloworld!!!";
    }

}
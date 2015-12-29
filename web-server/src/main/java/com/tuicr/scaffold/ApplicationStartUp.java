package com.tuicr.scaffold;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 项目启动完成初始化
 *
 * @author ylxia
 * @version 1.0
 * @package com.tuicr
 * @date 2015-08-21 15:51:19
 */
@Order
@Slf4j
@Component
public class ApplicationStartUp implements ApplicationListener<ContextRefreshedEvent> {

/*
    @Reference(
            version = DubboConst.Version.VERSION_ACCOUNT,
            group = DubboConst.Group.PROVIDER_ACCOUNT
    )
    private AcctSecurityProducer acctSecurityProducer;*/

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        log.debug("applicationContext={}", context);

    }


}
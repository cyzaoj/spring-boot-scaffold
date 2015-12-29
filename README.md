# spring-boot-scaffold
springboot脚手架，集成jpa freemarker dubbo security为一体




#web-server
适用于web项目,集成了修改了UsernamePasswordAuthenticationFilter加入了验证码的机制<br>
ApplicationStartUp项目启动完成调用<br>
CommonConfiguration配置spring最基本的配置<br>
DataSourceConfiguration配置数据源<br>
DubboAutoConfiguration用注解的方式配置dubbo<br>

项目配置文件加载统一配置在Bootstrap类中,暂时只放置了dbconfig.properties / platform.properties / logback.properties



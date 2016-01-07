# spring-boot-scaffold
springboot脚手架，集成jpa freemarker dubbo security为一体,使用不到的可以将对应的maven依赖删除即可.



#运行方式

1.执行com.tuicr.scaffold.Bootstrap的main即可运行服务端,相关容器配置项请修改application.yml <br>
2. 个人比较倾向于打成jar进行部署,运行方式java -jar target/xxxxxx-0.0.1-SNAPSHOT.jar <br>
远程调试运行方式java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar target/xxxxx-0.0.1-SNAPSHOT.jar




#web-server
适用于web项目,修改了UsernamePasswordAuthenticationFilter加入了验证码的机制<br>
ApplicationStartUp项目启动完成调用<br>
CommonConfiguration配置spring最基本的配置<br>
DataSourceConfiguration配置数据源<br>
DubboAutoConfiguration用注解的方式配置dubbo<br>

项目配置文件加载统一配置在Bootstrap类中,暂时只放置了dbconfig.properties / platform.properties


#restful-server

api服务端脚手架
拦截/api/**请求,通过header验证请求的合法性,(类似JWT)<br>


客户端实现秘钥生成可以参考DigestAuthUtils.generateDigest去完成实现<br>

注:druid未完全配置完成<br>
服务端经常涉及到性能问题,可以通过阿里巴巴的druid进行分析,为了演示这里没有对其uri进行拦截,访问地址:/druid/index.html

咋不图别的,就是想让我们的api更安全<br>




欢迎各位大神来吐槽.
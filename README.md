# spring-boot-scaffold
springboot脚手架，集成jpa freemarker dubbo security为一体,使用不到的可以将对应的maven依赖删除即可.

#目录结构


|____pom.xml                                    <br>
|____src                                        <br>                                       
| |____main                                     <br>
| | |____java                                   <br>
| | | |____com                                  <br>
| | | | |____tuicr                              <br>
| | | | | |____scaffold                         <br>
| | | | | | |____ApplicationStartUp.java        <br>
| | | | | | |____Bootstrap.java                 <br>
| | | | | | |____controller                     <br>
| | | | | | | |____api                          <br>
| | | | | | | | |____TestController.java        <br>
| | | | | | | |____RestfulApiAdvice.java                //请求切面监听,initBinder  ,exceptionHandler都可以在这配置           <br>
| | | | | | |____server                                                                                                 <br>
| | | | | | | |____config                               // spring相关配置                                                 <br>
| | | | | | | | |____CommonConfiguration.java                                                                           <br>
| | | | | | | | |____DataSourceConfiguration.java                                                                       <br>
| | | | | | | | |____DubboAutoConfiguration.java                                                                        <br>
| | | | | | | |____MvcConfig.java                                                                                       <br>
| | | | | | | |____properties                           //对应配置文件,boot自动装置相关配置到对应的bean                           <br>
| | | | | | | | |____DataSourceProperties.java                                                                              <br>
| | | | | | | | |____DubboApplication.java                                                                                  <br>
| | | | | | | | |____DubboProtocol.java                                                                                     <br>
| | | | | | | | |____DubboProvider.java                                                                                     <br>
| | | | | | | | |____DubboRegistry.java                                                                                     <br>
| | | | | | | |____SecurityConfig.java                   //springsecurity相关配置                                               <br>
| | | | | | | |____ServletContainerCustomizer.java        //配置错误跳转页面                                                    <br>
| | |____resources                                                                                                      <br>
| | | |____application.yml                          //基础配置,(必须)                                                     <br>
| | | |____dbconfig.properties                      //数据源配置                                                             <br>
| | | |____dubbo.properties                         //dubbo配置                                                               <br>
| | | |____i18n                                     //国际化资源文件                                                           <br>
| | | | |____message_en_US.properties                                                                                       <br>
| | | | |____message_zh_CN.properties                                                                                       <br>
| | | |____logback.xml                                                                                                      <br>
| | | |____platform.properties                      //本工程相关配置,根据需求自由定制                                                  <br>
<br>
<br>
<br>



如需要引入dubbo相关配置，请依赖https://github.com/cyzaoj/spring-boot-dubbo-starter&nbsp;
工程，系统将自动载入dubbo.properties相关配置


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

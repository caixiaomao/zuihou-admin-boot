package com.github.zuihou;

import com.github.zuihou.security.annotation.EnableLoginArgResolver;
import com.github.zuihou.validator.annotation.EnableFormValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * https://mp.weixin.qq.com/s/zkxI5IQP0jFTjVYe5pTsXw
 * EnableAspectJAutoProxy(proxyTargetClass=true, exposeProxy=true) 配合 @EnableCaching
 * 才能解决在同一个类中通过 AopContext.currentProxy() 调用时，使缓存生效
 *
 * @author zuihou
 * @createTime 2018-01-13 1:34
 */
@SpringBootApplication
@Configuration
@Slf4j
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableFormValidator
@EnableLoginArgResolver
//@EnableFeignClients("com.github.zuihou")
public class AuthorityApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(AuthorityApplication.class, args);
        Environment env = application.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! 访问连接:\n\t" +
                        "Swagger文档: \t\thttp://{}:{}/doc.html\n\t" +
                        "数据库监控: \t\thttp://{}:{}/druid\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                "127.0.0.1",
                env.getProperty("server.port"));
    }
}

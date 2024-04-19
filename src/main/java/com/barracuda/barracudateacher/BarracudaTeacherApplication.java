package com.barracuda.barracudateacher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = "com.barracuda")
public class BarracudaTeacherApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BarracudaTeacherApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        String serverPort = environment.getProperty("server.port");
        String serverServletContextPath = environment.getProperty("server.servlet.context-path");
        log.info("(♥◠‿◠)ﾉﾞ  BarracudaTeacher启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "http://localhost:{}{}", serverPort, serverServletContextPath);
    }

}

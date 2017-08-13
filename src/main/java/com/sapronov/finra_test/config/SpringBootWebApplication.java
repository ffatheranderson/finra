package com.sapronov.finra_test.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;

/**
 * Created by fa on 16.05.2017.
 */
@SpringBootApplication
@ComponentScan({"com.sapronov.finra_test.*"})
public class SpringBootWebApplication extends SpringBootServletInitializer {

    public static ApplicationContext CONTEXT;
    private static final Logger log = LoggerFactory.getLogger(SpringBootWebApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }

    public static void main(String[] args) throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-4"));
        String statusOfSecurityManager = System.getSecurityManager() == null ? "DISABLED" : "ENABLED";
        log.info("SECURITY MANAGER IS : " + statusOfSecurityManager);
        CONTEXT = SpringApplication.run(SpringBootWebApplication.class, args);

//        String[] beanNames = context.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
    }

}

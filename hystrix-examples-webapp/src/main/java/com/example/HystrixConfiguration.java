package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@Configuration
@EnableConfigurationProperties(HystrixProperties.class)
@ConditionalOnExpression("${hystrix.enabled:true}")
class HystrixConfiguration {
    @Autowired
    HystrixProperties hystrixProperties;
    
    @Bean
    @ConditionalOnClass(HystrixCommandAspect.class)
    HystrixCommandAspect hystrixCommandAspect() {
        return new HystrixCommandAspect();
    }
    @Bean
    @ConditionalOnClass(HystrixMetricsStreamServlet.class)
    @ConditionalOnExpression("${hystrix.streamEnabled:false}")
    public ServletRegistrationBean hystrixStreamServlet(){
        return new ServletRegistrationBean(new HystrixMetricsStreamServlet(), hystrixProperties.streamUrl);
    }
}
/**
 * Configuration properties for Hystrix.
 *
 */
@ConfigurationProperties(prefix = "hystrix", ignoreUnknownFields = true)
class HystrixProperties {
    boolean enabled = true;
    boolean streamEnabled = false;
    String streamUrl = "/hystrix.stream";
}
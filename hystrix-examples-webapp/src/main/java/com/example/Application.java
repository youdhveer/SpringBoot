package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@EnableCircuitBreaker
@RestController
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class Application extends SpringBootServletInitializer {
 
    @Autowired
    private BookService bookService;

    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
      return builder.build();
    }

    @RequestMapping("/to-read")
    public String toRead() {
    	try {
			bookService.testMethodViaHystrix("Test method via hystrix");
		} catch (Exception e) {
			System.out.println("exception : "+e.getMessage());
			e.printStackTrace();
		}
      return bookService.readingList();
    }
    
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
    public static void main(String[] args) {
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        ApplicationContext ctx = SpringApplication.run(Application.class, args);        
        
    }
    
    public void run(String... args) throws Exception {
        System.out.println("-------------------------------------run called-----------");
        bookService.testMethodViaHystrix("Test method via hystrix");
    }

}
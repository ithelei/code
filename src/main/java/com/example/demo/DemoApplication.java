package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

//组合注解

/**
 * @ServletComponentScan：使用该注解后，Servlet、Filter、Listener
 * 可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其它代码。
 *事实上，在Spring Boot中添加自己的Servlet、Filter和Listener有两种方法，
 *代码注册和注解自动注册。上面我们已经讲解了如何用注解自动祖册，
 * 而代码注册可以通过ServletRegistrationBean、FilterRegistrationBean和ServletListenerRegistrationBean注册Bean。
 * 虽然条条大路通罗马，但是我希望大家先掌握一种方式，一路走到底，而不是纠结于回子有几种写法。
 *
 */
@SpringBootApplication
@ServletComponentScan
@ImportResource(locations = {"classpath:spring-mvc.xml"})
//异步调用注解时
@EnableAsync
//重试机制
@EnableRetry
public class DemoApplication {

	public static void main(String[] args) {
		//应用程序开始运行的方法
		SpringApplication.run(DemoApplication.class, args);
	}


}


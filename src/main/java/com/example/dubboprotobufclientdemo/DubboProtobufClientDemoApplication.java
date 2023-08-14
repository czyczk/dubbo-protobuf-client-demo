package com.example.dubboprotobufclientdemo;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class DubboProtobufClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboProtobufClientDemoApplication.class, args);
	}

}

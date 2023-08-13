package com.example.dubboprotobufclientdemo;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboService
public class DubboProtobufClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboProtobufClientDemoApplication.class, args);
	}

}

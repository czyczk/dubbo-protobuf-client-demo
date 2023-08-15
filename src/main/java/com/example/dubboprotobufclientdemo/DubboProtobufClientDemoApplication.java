package com.example.dubboprotobufclientdemo;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class DubboProtobufClientDemoApplication {

	public static void main(String[] args) {
		// To fix the "java.lang.reflect.InaccessibleObjectException: Unable to make field final xxx accessible" issue,
		// follow the instructions on https://github.com/apache/dubbo/issues/9911#issuecomment-1154970447.
		SpringApplication.run(DubboProtobufClientDemoApplication.class, args);
	}

}

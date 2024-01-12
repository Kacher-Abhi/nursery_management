package com.nursery.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.nursery.management")
public class NurseryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(NurseryManagementApplication.class, args);
	}

}

package com.tuoke.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tuoke.business.mapper")
public class BusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessApplication.class, args);
	}

}

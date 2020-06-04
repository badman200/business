package com.tuoke.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tuoke.business.mapper")
public class BusinessApplication {

	public static void main(String[] args) {
		args[0]="mpw.key=2ecb06a08e06ab82";
		SpringApplication.run(BusinessApplication.class, args);
	}

}

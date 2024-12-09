package com.erp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.erp.dao")  // UserDAO가 포함된 패키지를 지정합니다.
public class ERPApplication {

	public static void main(String[] args) {
		SpringApplication.run(ERPApplication.class, args);
	}

}

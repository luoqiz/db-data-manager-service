package com.luoqiz.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.luoqiz.code.test.mapper")
@SpringBootApplication
public class CodeGeneratorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeGeneratorServiceApplication.class, args);
	}
}

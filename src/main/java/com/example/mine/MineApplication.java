package com.example.mine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class) // Spring security無効化
@MapperScan("com.example.mine.mapper")
public class MineApplication {

	public static void main(String[] args) {
		SpringApplication.run(MineApplication.class, args);
	}

}

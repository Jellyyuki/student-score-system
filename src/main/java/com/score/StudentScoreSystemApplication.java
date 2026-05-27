package com.score;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.score.mapper")
public class StudentScoreSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudentScoreSystemApplication.class, args);
	}
}

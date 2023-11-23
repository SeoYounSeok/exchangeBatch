package com.main.exchangeBatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExchangeBatchApplication {

	public static void main(String[] args) {
		// 스프링 애플리케이션을 실행합니다.
		SpringApplication.run(ExchangeBatchApplication.class, args);
	}
}

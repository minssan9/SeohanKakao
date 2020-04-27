package com.kakao;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@EnableBatchProcessing
@SpringBootApplication 
@PropertySource(value = {"classpath:account.properties" })  
public class KakaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaoApplication .class, args);
	}
}

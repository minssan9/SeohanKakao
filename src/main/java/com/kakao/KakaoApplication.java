package com.kakao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication 
@PropertySource(value = {"classpath:account.properties" })  
public class KakaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaoApplication .class, args);
	}
}

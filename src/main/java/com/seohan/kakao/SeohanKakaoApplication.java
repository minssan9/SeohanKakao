package com.seohan.kakao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySource(value = {"classpath:account.properties" }) 
@Configuration
public class SeohanKakaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeohanKakaoApplication .class, args);
	}
}

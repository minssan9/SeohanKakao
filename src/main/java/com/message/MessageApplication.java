package com.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.time.format.DateTimeFormatter;

@SpringBootApplication(scanBasePackages = {"com.message.*"})
@PropertySource(value = {"classpath:account.properties" })
public class MessageApplication {
	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}
}

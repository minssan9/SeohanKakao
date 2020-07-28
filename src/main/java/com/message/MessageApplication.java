package com.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:account.properties" })
public class MessageApplication {
	private Logger logger = LoggerFactory.getLogger(MessageApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication .class, args); 
	}
}

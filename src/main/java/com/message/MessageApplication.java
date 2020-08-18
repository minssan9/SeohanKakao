package com.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.text.SimpleDateFormat;

@SpringBootApplication
@Configuration
@SpringBootConfiguration
@PropertySource(value = {"classpath:account.properties" })
public class MessageApplication {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private Logger logger = LoggerFactory.getLogger(MessageApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication .class, args); 
	}
}

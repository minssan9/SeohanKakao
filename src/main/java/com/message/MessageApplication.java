package com.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.time.format.DateTimeFormatter;

@SpringBootApplication
@PropertySource(value = {"classpath:account.properties" })
public class MessageApplication {
	public static DateTimeFormatter dateFormatString = DateTimeFormatter.ofPattern("yyyyMMdd");
	public static DateTimeFormatter timeFormatString = DateTimeFormatter.ofPattern("HHmm");

	public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");


	private Logger logger = LoggerFactory.getLogger(MessageApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication .class, args); 
	}
}

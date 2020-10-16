package com.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.time.format.DateTimeFormatter;

@SpringBootApplication(scanBasePackages = {"com.message.*"})
@PropertySource(value = {"classpath:account.properties" })
public class MessageApplication {
	public static DateTimeFormatter dateString = DateTimeFormatter.ofPattern("yyyyMMdd");
	public static DateTimeFormatter timeString = DateTimeFormatter.ofPattern("HHmm");

	public static DateTimeFormatter dateFormatString = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static DateTimeFormatter timeFormatString = DateTimeFormatter.ofPattern("HH:mm");

	public static DateTimeFormatter dateTimeFormatString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication .class, args); 
	}
}

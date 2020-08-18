package com.message.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.message.service.MessageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.message.dto.MessageDto;
import com.message.mssql.domain.GrapMessageModel;
import com.message.service.GrapService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class MessageTest {


	@Autowired
	private MessageService messageService;

	@Test
	public void test() throws Exception {

		MessageDto messageDto = new MessageDto().builder()
				.receiverId("4150149")
				.email("4150149@seohan.com")
				.subject("test push")
				.content("테스트 발송")
				.text("테스트 발송")
				 .build();

//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.postForObject("http://211.40.184.7:5090/kakao/save", messageDto2, String.class);

		MessageDto resultMessageDto  = new MessageDto();
		resultMessageDto = messageService.save(messageDto);

		assertEquals(messageDto, resultMessageDto);
	}
}

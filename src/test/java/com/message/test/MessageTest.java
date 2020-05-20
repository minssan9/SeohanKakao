package com.message.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.message.dto.MessageDto;
import com.message.mssql.domain.GrapMessageModel;
import com.message.service.GrapService;
import org.springframework.web.client.RestTemplate;

public class MessageTest {


	@Autowired
	private GrapService grapService;

	@Test
	public void test() throws Exception {
		
		MessageDto messageDto = new MessageDto().builder()
				.receiverId("4150149")
				.email("4150149")
				.content("테스트 발송")
				.text("테스트 발송")
				 .build();

		MessageDto messageDto2 = new MessageDto().builder()
				.company("ENP")
				.receiverId("6190037")
				.subject("test push")
				.content("테스트 발송")
				.text("테스트 발송")
				.build();

//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.postForObject("http://211.40.184.7:5090/kakao/save", messageDto2, String.class);
		GrapMessageModel grapMessageModelCreated = new GrapMessageModel();
		grapMessageModelCreated = grapService.save(messageDto2);
		
		assertEquals("OK", grapMessageModelCreated.getResponse());
	}

}

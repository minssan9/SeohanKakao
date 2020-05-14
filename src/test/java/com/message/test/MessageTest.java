package com.message.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.message.dto.MessageDto;
import com.message.mssql.domain.GrapMessageModel;
import com.message.service.GrapService;

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
		 
		GrapMessageModel grapMessageModelCreated = new GrapMessageModel();
		grapMessageModelCreated = grapService.save(messageDto);
	}

}

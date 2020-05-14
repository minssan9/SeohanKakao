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
	public void test() {
		fail("Not yet implemented");
		MessageDto messageDto = new MessageDto();
		messageDto.builder()
		 .receiverId("4150149")
		 .text("테스트 발송");		 
		 
		GrapMessageModel grapMessageModelCreated = new GrapMessageModel(); 
		try {
			grapMessageModelCreated = grapService.save(messageDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

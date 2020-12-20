package com.message.test;

import com.message.dto.MessageDto;
import com.message.dto.ReceiverDto;
import com.message.service.MessageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MessageTest {
	@Autowired
	private MessageService messageService;

	@Test
	public void test() throws Exception {
		List<ReceiverDto> receiverDtoLIst = new ArrayList<>();
		receiverDtoLIst.add(ReceiverDto.builder()
							.receiverId("4150149")
							.email("4150149@seohan.com")
							.build());

		MessageDto messageDto = new MessageDto().builder()
				.subject("test push")
				.content("테스트 발송")
				.text("테스트 발송")
				.receivers(receiverDtoLIst)
				 .build();

		messageDto  = messageService.send(messageDto);


	}
}

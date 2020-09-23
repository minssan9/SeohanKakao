package com.message.service;

import com.message.dto.MessageDto;

import java.util.List;

public interface MessageService {
	MessageDto save(MessageDto messageDto); 
	
	List<MessageDto> save(List<MessageDto> messageDtos);

	MessageDto saveByList(MessageDto messageDto);

	public boolean isNumeric( String str);
}

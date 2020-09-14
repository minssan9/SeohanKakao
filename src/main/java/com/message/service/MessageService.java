package com.message.service;

import java.util.List;

import com.message.dto.MessageDto;

public interface MessageService { 
	MessageDto save(MessageDto messageDto); 
	
	List<MessageDto> save(List<MessageDto> messageDtos); 
	
	public boolean isNumeric( String str);
}

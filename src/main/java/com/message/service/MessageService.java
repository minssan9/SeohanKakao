package com.message.service;

import com.message.dto.MessageDto;

public interface MessageService { 
	MessageDto save(MessageDto messageDto) throws Exception; 

	String getMessagetype(MessageDto messageDto);

	boolean isNumeric( String str);
}

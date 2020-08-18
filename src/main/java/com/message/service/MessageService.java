package com.message.service;

import com.message.dto.MessageDto;

public interface MessageService { 
	MessageDto save(MessageDto messageDto) throws Exception; 
	
	public boolean isNumeric( String str);
}

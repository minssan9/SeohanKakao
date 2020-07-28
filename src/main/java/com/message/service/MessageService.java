package com.message.service;

import com.message.dto.MessageDto;
import com.message.mssql.domain.GrapMessageModel;
 
public interface MessageService { 
	MessageDto save(MessageDto messageDto) throws Exception; 
	
	public boolean isNumeric( String str);
}

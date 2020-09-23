package com.message.service;

import com.message.dto.MessageDto;
 
public interface GrapService { 
	MessageDto save(MessageDto messageDto) throws Exception;

	MessageDto send(MessageDto messageDto) throws Exception;

	MessageDto makeMessage(MessageDto messageDto);

}

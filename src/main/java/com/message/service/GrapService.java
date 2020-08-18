package com.message.service;

import com.message.dto.MessageDto;
import com.message.domain.GrapMessageModel;
 
public interface GrapService { 
	MessageDto save(MessageDto messageDto) throws Exception;
	GrapMessageModel makeMessage(MessageDto messageDto);
}

package com.message.service;

import com.message.dto.MessageDto;

import java.util.List;

public interface MessageService {
	MessageDto send(MessageDto messageDto) throws Exception;
	List<MessageDto> send(List<MessageDto> messageDtos) throws Exception;
}
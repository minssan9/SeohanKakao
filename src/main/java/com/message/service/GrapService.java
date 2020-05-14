package com.message.service;

import com.message.dto.MessageDto;
import com.message.mssql.domain.GrapMessageModel;
 
public interface GrapService { 
	GrapMessageModel save(MessageDto messageDto) throws Exception;
}

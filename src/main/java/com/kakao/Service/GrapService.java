package com.kakao.Service;

import com.kakao.Dto.MessageDto;
 
public interface GrapService { 
	MessageDto save(MessageDto messageDto) throws Exception;
}

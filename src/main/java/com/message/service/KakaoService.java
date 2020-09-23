package com.message.service;

import com.message.dto.KakaoDto;
import com.message.dto.MessageDto;
import com.message.domain.KakaoMessageModel;
 
public interface KakaoService { 
	KakaoMessageModel save(MessageDto messageDto) throws Exception;

	KakaoMessageModel send(KakaoDto kakaoDto) throws Exception;
}

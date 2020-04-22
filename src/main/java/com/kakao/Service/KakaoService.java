package com.kakao.Service;

import com.kakao.Dto.MessageDto;
import com.kakao.domain.KakaoMessageModel;
 
public interface KakaoService { 
	KakaoMessageModel save(MessageDto messageDto) throws Exception;
}

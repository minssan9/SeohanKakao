package com.kakao.Service;

import com.kakao.Dto.MessageDto;
import com.kakao.seohan.domain.SeohanMessageModel;
 
public interface GrapService { 
	MessageDto save(MessageDto messageDto) throws Exception;
}

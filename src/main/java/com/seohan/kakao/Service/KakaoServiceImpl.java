package com.seohan.kakao.Service;

import java.text.SimpleDateFormat;

import com.seohan.kakao.Domain.KakaoMessageModel;
import com.seohan.kakao.Mapper.KakaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KakaoServiceImpl implements KakaoService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
 
    @Autowired
    KakaoRepository kakaoRepository;
        
	@Override 
	public KakaoMessageModel save(KakaoMessageModel kakaoMessageModel) throws Exception  {  
		kakaoRepository.sendMessage(kakaoMessageModel.getTemplate_code(), kakaoMessageModel.getRecipient_num(), kakaoMessageModel.getContent() );
		kakaoRepository.save(kakaoMessageModel);
		return kakaoMessageModel;
	} 
}

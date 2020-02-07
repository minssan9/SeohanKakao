package com.seohan.kakao.Service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seohan.kakao.Domain.KakaoMessageModel;
import com.seohan.kakao.Mapper.KakaoRepository;

@Service
public class KakaoServiceImpl implements KakaoService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
 
    @Autowired
    KakaoRepository KakaoRepository;
        
	@Override 
	public KakaoMessageModel save(KakaoMessageModel kakaoMessageModel) throws Exception  {  
		KakaoRepository.save(kakaoMessageModel );	 
		return kakaoMessageModel;
	} 
}

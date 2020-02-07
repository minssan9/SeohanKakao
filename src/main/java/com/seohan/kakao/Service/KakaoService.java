package com.seohan.kakao.Service;

import com.seohan.kakao.Domain.KakaoMessageModel;
 
public interface KakaoService { 
	KakaoMessageModel save(KakaoMessageModel kakaoMessageModel) throws Exception;
}

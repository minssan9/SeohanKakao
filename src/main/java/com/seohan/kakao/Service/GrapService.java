package com.seohan.kakao.Service;

import com.seohan.kakao.Domain.KakaoMessageModel;
 
public interface GrapService { 
	KakaoMessageModel save(KakaoMessageModel kakaoMessageModel) throws Exception;
}

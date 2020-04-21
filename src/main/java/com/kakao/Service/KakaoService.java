package com.kakao.Service;

import com.kakao.seohan.domain.SeohanMessageModel;
 
public interface KakaoService { 
	SeohanMessageModel save(SeohanMessageModel kakaoMessageModel) throws Exception;
}

package com.kakao.Service;

import com.kakao.seohan.domain.SeohanMessageModel;
 
public interface GrapService { 
	SeohanMessageModel save(SeohanMessageModel kakaoMessageModel) throws Exception;
}

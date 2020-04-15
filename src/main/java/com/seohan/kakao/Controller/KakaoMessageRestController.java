package com.seohan.kakao.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.seohan.kakao.Domain.KakaoMessageModel;
import com.seohan.kakao.Service.KakaoService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/kakao")
@Slf4j
@RestController
class KakaoMessageRestController {
	@Autowired
	private KakaoService kakaoService;

	@PostMapping("/save")
	public ResponseEntity<String> createKakaoMessage(@RequestBody KakaoMessageModel kakaoMessageModel ) throws Exception   { 		
		System.out.println(kakaoMessageModel.toString());
		
		if(isNumeric(kakaoMessageModel.getMt_refkey().substring(0,1))) {
			// grap massenger http Post 
//
//			URL url = new URL("http://localhost:8190/kakao/save"); // URL 설정 
//			// RestTemplate 에 MessageConverter 세팅
//		    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
//		    converters.add(new FormHttpMessageConverter());
//		    converters.add(new StringHttpMessageConverter());
//		 
//		    RestTemplate restTemplate = new RestTemplate();
//		    restTemplate.setMessageConverters(converters); 
//		 
//		    // REST API 호출
//		    String result = restTemplate.postForObject(url.toString(), kakaoMessageModel , String.class); 
			return new ResponseEntity<String>("OK", HttpStatus.OK);	
		}else		{
			KakaoMessageModel KakaoMessageModelCreated = kakaoService.save(kakaoMessageModel );		
			return new ResponseEntity<String>(KakaoMessageModelCreated.getMt_refkey(), HttpStatus.OK);	
		}		
		
	}

	public static boolean isNumeric(final String str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
}

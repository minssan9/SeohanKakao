package com.seohan.kakao.Controller;

import com.seohan.kakao.Domain.KakaoMessageModel;
import com.seohan.kakao.Dto.KakaoMessageDto;
import com.seohan.kakao.Service.KakaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/kakao")
@Slf4j
@RestController
class KakaoMessageRestController {
	@Autowired
	private KakaoService kakaoService;

	@PostMapping("/save")
	public ResponseEntity<String> createKakaoMessage(@RequestBody KakaoMessageDto kakaoMessageDto ) throws Exception   { 		
		System.out.println(kakaoMessageDto.toString());
		
		if(isNumeric(kakaoMessageDto.getMt_refkey().substring(0,1))) {
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
			KakaoMessageModel kakaoMessageModel = new KakaoMessageModel();
			kakaoMessageModel.setSubject(kakaoMessageDto.getSubject());
			kakaoMessageModel.setContent(kakaoMessageDto.getContent());
			kakaoMessageModel.setRecipient_num(kakaoMessageDto.getRecipient_num());
			kakaoMessageModel.setTemplate_code(kakaoMessageDto.getTemplate_code()); 

			KakaoMessageModel KakaoMessageModelCreated = kakaoService.save(kakaoMessageModel );		
			return new ResponseEntity<String>("OK", HttpStatus.OK);	
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

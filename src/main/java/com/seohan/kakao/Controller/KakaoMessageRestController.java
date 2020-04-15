package com.seohan.kakao.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
			KakaoMessageModel KakaoMessageModelCreated = kakaoService.save(kakaoMessageModel );		
			return new ResponseEntity<String>(KakaoMessageModelCreated.getMt_refkey(), HttpStatus.OK);	
		}else		{
			// grap massenger http Post 
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

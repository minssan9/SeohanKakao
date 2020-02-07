package com.seohan.kakao.Controller;

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
	 
	@PostMapping("save")
	public ResponseEntity<KakaoMessageModel> createKakaoMessageModel(@RequestBody KakaoMessageModel kakaoMessageModel ) throws Exception   { 		
		KakaoMessageModel KakaoMessageModelCreated = kakaoService.save(kakaoMessageModel );
		return new ResponseEntity<KakaoMessageModel>(KakaoMessageModelCreated, HttpStatus.OK);
	}	 
}

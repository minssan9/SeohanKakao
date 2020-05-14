package com.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.message.dto.MessageDto;
import com.message.mssql.domain.GrapMessageModel;
import com.message.mssql.domain.KakaoMessageModel;
import com.message.service.GrapService;
import com.message.service.KakaoService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/kakao")
@Slf4j 
@RestController
class MessageRestController {
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private GrapService grapService;

	@PostMapping("/save")
	public ResponseEntity<String> createKakaoMessage(@RequestBody MessageDto messageDto ) throws Exception   {		
		if(isNumeric(messageDto.getAccountId().substring(0,1))) {  			// grap massenger http Post   
			GrapMessageModel grapMessageModelCreated = grapService.save(messageDto );
			KakaoMessageModel kakaoMessageModelCreated = kakaoService.save(messageDto );	
			
			return new ResponseEntity<String>(grapMessageModelCreated.getResponse(), HttpStatus.OK);
		}else		{ 
			KakaoMessageModel kakaoMessageModelCreated = kakaoService.save(messageDto );					
			return new ResponseEntity<String>(kakaoMessageModelCreated.getReport_code(), HttpStatus.OK);	
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

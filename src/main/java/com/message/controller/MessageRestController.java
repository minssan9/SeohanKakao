package com.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public @ResponseBody ResponseEntity<String> createKakaoMessage(@RequestBody MessageDto messageDto ) throws Exception   {

		if(messageDto.getReceiverId()==null){
			KakaoMessageModel kakaoMessageModelCreated = kakaoService.save(messageDto );
			return new ResponseEntity<String>(kakaoMessageModelCreated.getReport_code(), HttpStatus.OK);
//			return new ResponseEntity<String>("unRegistered Id ", HttpStatus.BAD_REQUEST);
		}else{
			String employeeType = messageDto.getReceiverId().substring(0,1);

			if(isNumeric(employeeType)) {
				GrapMessageModel grapMessageModelCreated = grapService.save(messageDto );
			}else{
//				KakaoMessageModel kakaoMessageModelCreated = kakaoService.save(messageDto );
//				return new ResponseEntity<String>(kakaoMessageModelCreated.getReport_code(), HttpStatus.OK);
			}
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

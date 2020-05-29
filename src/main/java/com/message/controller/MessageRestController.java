package com.message.controller;

import com.message.dto.MessageDto;
import com.message.mssql.domain.GrapMessageModel;
import com.message.mssql.domain.KakaoMessageModel;
import com.message.service.GrapKamtecService;
import com.message.service.GrapService;
import com.message.service.KakaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/kakao")
@Slf4j 
@RestController
class MessageRestController {
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private GrapService grapService;

	@Autowired
	private GrapKamtecService grapKamtecService;

	@PostMapping("/save")
	public @ResponseBody ResponseEntity<String> createKakaoMessage(@RequestBody MessageDto messageDto ) throws Exception   {
		KakaoMessageModel kakaoMessageModelCreated = new KakaoMessageModel();

		if(messageDto.getReceiverId()==null || messageDto.getReceiverId().equals("")){			//	사번 공백이거나 누락시 카카오 메시지 발송
			kakaoMessageModelCreated = kakaoService.save(messageDto );
			return new ResponseEntity<String>(kakaoMessageModelCreated.getReport_code(), HttpStatus.OK);
//			return new ResponseEntity<String>("unRegistered Id ", HttpStatus.BAD_REQUEST);
		}else{																								
			String employeeType = messageDto.getReceiverId().substring(0,1);
			
			if(isNumeric(employeeType) && !messageDto.getReceiverId().equals("4027090")) {			//		사번 첫자리 숫자 -> 사내 직원이면 grap 발송
				GrapMessageModel grapMessageModelCreated = grapService.save(messageDto );
				if(messageDto.getCompany().equals("KAMTEC")){			//캄텍은 둘다 발송
					kakaoService.save(messageDto );				
				}		
			}else{
				kakaoService.save(messageDto );				
			}			 
		}
		return new ResponseEntity<String>(kakaoMessageModelCreated.getReport_code(), HttpStatus.OK);
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

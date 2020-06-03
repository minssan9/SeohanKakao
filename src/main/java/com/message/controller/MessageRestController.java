package com.message.controller;

import com.message.dto.MessageDto;
import com.message.mssql.domain.GrapMessageModel;
import com.message.mssql.domain.KakaoMessageModel;
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

	@PostMapping("/save")
	public @ResponseBody ResponseEntity<String> createKakaoMessage(@RequestBody MessageDto messageDto ) throws Exception {
		KakaoMessageModel kakaoMessageModelCreated = new KakaoMessageModel();
		String employeeType = "";
		String receiverId = "";
		if (messageDto.getReceiverId()!=null && !messageDto.getReceiverId().equals("")) {
			receiverId  = messageDto.getReceiverId();
			employeeType = messageDto.getReceiverId().substring(0,1);
		}else {
			System.out.println(messageDto.getText());
		}

		//	사번 공백, 누락, 숫자로 시작 안할 경우 카카오 메시지 발송
		if( !isNumeric(employeeType) || receiverId.equals("4027090") ){
			kakaoMessageModelCreated = kakaoService.save(messageDto );
			return new ResponseEntity<String>(kakaoMessageModelCreated.getReport_code(), HttpStatus.OK);
//			return new ResponseEntity<String>("unRegistered Id ", HttpStatus.BAD_REQUEST);
		}else{
			GrapMessageModel grapMessageModelCreated = new GrapMessageModel();
			switch (messageDto.getCompany()){
				case "KAMTEC":
					grapMessageModelCreated = grapService.save(messageDto );
					kakaoService.save(messageDto );
					break;
				case "SEOHAN":
				case "ENP":
					grapMessageModelCreated = grapService.save(messageDto );
					break;
				default:
					break;
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

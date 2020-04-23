package com.kakao.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.Dto.MessageDto;
import com.kakao.Service.GrapService;
import com.kakao.Service.KakaoService;
import com.kakao.domain.KakaoMessageModel;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/kakao")
@Slf4j 
@RestController
class KakaoMessageRestController {
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private GrapService grapService;

	@PostMapping("/save")
	public ResponseEntity<String> createKakaoMessage(@RequestBody MessageDto messageDto ) throws Exception   { 		
		String result;
		if(isNumeric(messageDto.getAccountId().substring(0,1))) {  
			// grap massenger http Post  
			
			MessageDto messageDtoCreated = grapService.save(messageDto );
			result = messageDtoCreated.getResult();
			return new ResponseEntity<String>(result , HttpStatus.OK);	 
		}else		{ 
			KakaoMessageModel kakaoMessageModelCreated = kakaoService.save(messageDto );					
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

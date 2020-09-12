package com.message.service;

import com.message.dto.MessageDto;
import com.message.domain.KakaoMessageModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
	public static final String GRAP_MESSAGE_TYPE = "GRAP";
	public static final String KAKAO_MESSAGE_TYPE = "KAKAO";

	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private GrapService grapService;
    
	@Override 
	public MessageDto  save(MessageDto messageDto) throws Exception  { 
		KakaoMessageModel kakaoMessageModelCreated = new KakaoMessageModel();

		//	사번 공백, 누락, 숫자로 시작 안할 경우 카카오 메시지 발송
		if ( messageDto.getMessagetype().equals(GRAP_MESSAGE_TYPE)){
			messageDto = grapService.save(messageDto );
		}else if  ( messageType.equals(KAKAO_MESSAGE_TYPE)){
			kakaoMessageModelCreated = kakaoService.save(messageDto );
		}

		return messageDto;
	}

	public void getMessagetype(MessageDto messageDto) {

		if (messageDto.getReceiverId()!=null && !messageDto.getReceiverId().equals("")) {
			messageDto.setMessagetype( GRAP_MESSAGE_TYPE);
		}else if (messageDto.getReceiverId().equals("4027090")){

		}else{
			messageDto.setMessagetype( KAKAO_MESSAGE_TYPE);
			log.warn("Kakao Message will be sended");
		}

		if( !isNumeric(employeeType) ){
			messageDto.setResult(kakaoMessageModelCreated.getReport_code());
		}else{
		}
	}

	@Override
	public boolean isNumeric(String str) {
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

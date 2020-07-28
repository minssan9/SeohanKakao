package com.message.service;

import java.text.SimpleDateFormat;

import com.message.dto.MessageDto;
import com.message.mssql.domain.KakaoMessageModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private GrapService grapService;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
	@Override 
	public MessageDto  save(MessageDto messageDto) throws Exception  { 
		KakaoMessageModel kakaoMessageModelCreated = new KakaoMessageModel();		
		String employeeType = "";
		String receiverId = "";

		if (messageDto.getReceiverId()!=null && !messageDto.getReceiverId().equals("")) {
			receiverId  = messageDto.getReceiverId();
			employeeType = messageDto.getReceiverId().substring(0,1);
		}else {
			log.error(messageDto.getText());
		}

		//	사번 공백, 누락, 숫자로 시작 안할 경우 카카오 메시지 발송
		if( !isNumeric(employeeType) || receiverId.equals("4027090") ){
			kakaoMessageModelCreated = kakaoService.save(messageDto ); 
			messageDto.setResult(kakaoMessageModelCreated.getReport_code());
		}else{
			messageDto = grapService.save(messageDto ); 
		}		
		return messageDto;
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

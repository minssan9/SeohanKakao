package com.message.service;

import com.message.domain.KakaoMessageModel;
import com.message.dto.MessageDto;
import com.message.dto.Receiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
	public MessageDto  save(MessageDto messageDto) { 
		KakaoMessageModel kakaoMessageModelCreated = new KakaoMessageModel();		
		String employeeType = "";
		String receiverId = "";

		log.info(messageDto.toString());
		
		try{
			if (messageDto.getReceiverId()!=null && !messageDto.getReceiverId().equals("")) {
				receiverId  = messageDto.getReceiverId();
				employeeType = messageDto.getReceiverId().substring(0,1);
			}else {
				log.info("Kakao Message will be sended");
			}

			//	사번 공백, 누락, 숫자로 시작 안할 경우 카카오 메시지 발송
			if( !isNumeric(employeeType) || receiverId.equals("4027090") ){
				kakaoMessageModelCreated = kakaoService.save(messageDto ); 
				messageDto.setResult(kakaoMessageModelCreated.getReport_code());
			}else{
				messageDto = grapService.send(messageDto );
			}		
			return messageDto;
		} catch(Exception e){
			log.error(e.toString());
			return messageDto;
		} 
	}


	@Override 
	public List<MessageDto>  save(List<MessageDto> messageDtos)   {
		log.info(messageDtos.toString());
		
		for (MessageDto messageDto:messageDtos) {
			save(messageDto);
		}
		return messageDtos;
	}


	@Override
	public MessageDto saveByList(MessageDto messageDto)   {
		log.info(messageDto.toString());

		for (Receiver receiver:messageDto.getReceivelist()) {
			messageDto.setCompany(receiver.getCompany());
			messageDto.setEmail(receiver.getEmail());
			messageDto.setRecipient_num(receiver.getRecipient_num());
			messageDto.setReceiverId(receiver.getReceiverId());
			save(messageDto);
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

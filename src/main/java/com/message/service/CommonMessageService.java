package com.message.service;

import com.message.dto.MessageDto;
import com.message.dto.MessageServiceType;
import com.message.dto.ReceiverDto;
import com.message.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommonMessageService {
	@Autowired	private BeanFactory beanFactory;
	@Autowired private CommonUtil commonUtil;

	public String sendMessage(List<MessageDto> messageDtos) throws Exception {
		for (MessageDto messageDto : messageDtos){
			for (ReceiverDto receiverDto :messageDto.getReceivers()) {
				messageDto.setEmail(receiverDto.getEmail());
				messageDto.setReceiverId (receiverDto.getReceiverId());
				messageDto.setRecipient_num (receiverDto.getRecipient_num());

				MessageServiceType messageServiceType = MessageServiceType.getMessageServiceType(getMessageType(receiverDto));
				((MessageService)beanFactory.getBean(messageServiceType.getServiceClassName())).send(messageDto);
			}
		}
		return "";
	}


	public String getMessageType (ReceiverDto receiverDto){
		String messageType = "";
		if (receiverDto.getReceiverId()!=null && !receiverDto.getReceiverId().equals("")) {
			if( !commonUtil.isNumeric(
					receiverDto.getReceiverId().substring(0,1)
				) || receiverDto.getReceiverId().equals("4027090") ){
				messageType = "KAKAO";
			}else {
				messageType = "GRAP";
			}
		}else {
			messageType = "KAKAO";
		}
		return messageType;
	}
}

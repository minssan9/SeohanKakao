package com.kakao.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data 
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class MessageDto {
	private String company; 
	private String email; 
//kakao 
	
	private String mt_refkey; 
	private String accountId; 
	private String subject;
	private String content; 
	private String msg_status;
	private String recipient_num; 
	private String country_code; 
	private String template_code;  

//grap 
	private String cpKeySpec;
	private String senderSno;
	private String receiverId;
	private String text;

	private String code;
	private String codeno;
	private String msg;
	private String data;

	private String sendNo;
	private String sendName;
	private String result;
}
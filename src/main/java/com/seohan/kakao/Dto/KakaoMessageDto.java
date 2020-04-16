package com.seohan.kakao.Dto;

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
public class KakaoMessageDto {    
	private String mt_refkey; 
	private String subject;
	private String content; 
	private String msg_status;
	private String recipient_num; 
	private String country_code; 
	private String template_code;  
}
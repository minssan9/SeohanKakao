package com.message.dto;

import lombok.*;

import java.util.List;

@Data 
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class MessageDto {
	private String company; 
	private String email;
	private String senderEmail;
	private String receiverEmail;
//kakao
	private String subject;
	private String content;
	private String recipient_num;
	private String template_code;
	private String result;
//grap
	private String senderSno;
	private String receiverId;
	private String text;

	private String sendNo;
	private String sendName;

	private List<Receiver> receivelist;
	public List<Receiver> getReceiverList(){
		return receivelist;
	}
}
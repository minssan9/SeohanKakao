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
	private String senderEmail;
	private String sendNo;
	private String sendName;

//kakao
	private String subject;
	private String content;
	private String template_code;
	private String result;

	private String recipient_num;
//grap
	private String senderSno;
	private String text;

	private String receiverId;


	private String email;
	private String receiverEmail;

	private List<Receiver> receivelist;
	public List<Receiver> getReceiverList(){
		return receivelist;
	}
}
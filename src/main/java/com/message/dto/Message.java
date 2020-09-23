package com.message.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {

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

    private String sendNo;
    private String sendName;
}

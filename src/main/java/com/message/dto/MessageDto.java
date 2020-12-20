package com.message.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
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
    @Setter
    private String result;
    @Setter
    private String recipient_num;
    private String mt_refkey;
    private String msg_status;
    private String country_code;
    private String accountId;
    //grap
    @Setter
    private String senderSno;
    @Setter
    private String text;
    @Setter
    private String receiverId;
    @Setter
    private String email;
    @Setter
    private String receiverEmail;

    private List<ReceiverDto> receivers;

    public MessageDto() {
    }

    ;

    public MessageDto(String receiverId, String recipient_num, String email) {
        this.receiverId = receiverId;
        this.recipient_num = recipient_num;
        this.email = email;
    }

    ;


}
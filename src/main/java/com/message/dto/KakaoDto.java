package com.message.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoDto {
    private String company;
    private String mt_refkey;
    private String subject;
    private String content;
    private String msg_status;
    private String recipient_num;
    private String country_code;
    private String template_code;

    private String accountId;
    private String sendName;
    private String sendNo;

}

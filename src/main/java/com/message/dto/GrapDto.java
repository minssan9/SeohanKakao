package com.message.dto;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrapDto {
    private String subject;
    private String email;
    private String receiverId;
    private String text;

    private String senderSno;
}

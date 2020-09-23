package com.message.dto;

import lombok.*;

@Data
@Builder
public class Receiver {
    private String company;
    private String receiverId;
    private String recipient_num;
    private String email;
}

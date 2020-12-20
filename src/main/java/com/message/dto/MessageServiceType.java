package com.message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum MessageServiceType {
    KAKAO("KAKAO", "kakaoService"),
    GRAP("GRAP", "grapService");

    private String messageCode;
    private String serviceClassName;

    private static Map<String, MessageServiceType> svcMap = new HashMap<>();
    static {
        for (MessageServiceType messageServiceType: values()){
            svcMap.put(messageServiceType.getMessageCode(), messageServiceType);
        }
    }
    public static MessageServiceType getMessageServiceType(String messageCode){
        return svcMap.get(messageCode);
    }
}

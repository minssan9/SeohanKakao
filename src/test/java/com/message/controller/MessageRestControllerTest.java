package com.message.controller;

import com.message.dto.MessageDto;
import com.message.dto.Receiver;
import com.message.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MessageRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MessageService messageService;

    @Test
    public void 메시지테스트() throws Exception {
        List<Receiver> receiverList = new ArrayList<>();
        Receiver receiver = Receiver.builder()
                .receiverId("4150149")
                .company("SEOHAN")
                .email("4150149@seohan.com")
                .recipient_num("01067766160")
                .build();
        receiverList.add(receiver);

        MessageDto messageDto = MessageDto.builder()
                .receivelist(receiverList)
                .content(" test 발송 ")
                .text(" test 발송 ")
                .sendNo("01234567890")
                .sendName("민상훈")
                .subject("제목")
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/message/save")
                        .contentType("application/json")
                    .content(messageDto.toString())
        )
                .andExpect(status().isOk());

    }
}
package com.message.service;

import com.message.config.RestTemplateHttp;
import com.message.domain.GrapMessageModel;
import com.message.dto.MessageDto;
import com.message.mapper.kamtec.KamtecGrapRepository;
import com.message.mapper.seohan.SeohanGrapRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import static com.message.util.CommonUtil.dateTimeFormatString;

@Service
@Slf4j
public class GrapServiceImpl implements MessageService {
    @Value("${grap.apiUrl}")
    static String grapUrl;
    @Value("${grap.apiKey}")
    static String CP_KEY_SPEC;
    @Value("${grap.seohan.senderSno}")
    String seohanSenderSno;
    @Value("${grap.kamtec.senderSno}")
    String kamtecSenderSno;
    @Autowired
    SeohanGrapRepository seohanGrapRepository;
    @Autowired
    KamtecGrapRepository kamtecGrapRepository;
    @Autowired
    RestTemplateHttp restTemplateHttp;

    @Override
    public MessageDto send(MessageDto messageDto) throws Exception {
        messageDto = makeMessage(messageDto);

        RestTemplate restTemplate = restTemplateHttp.getRestTemplate();
        JSONParser jsonParser = new JSONParser();
        URL url = new URL(grapUrl); // URL 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("cp-key-spec", CP_KEY_SPEC);

        HttpEntity requestEntity = new HttpEntity<>(messageDto, headers);


        ResponseEntity response = restTemplate.postForEntity(url.toString(), requestEntity, String.class);

        JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString());

        if (jsonObject.get("msg") != null) messageDto.setResult(jsonObject.get("msg").toString());

        return save(messageDto);
    }

    @Override
    public List<MessageDto> send(List<MessageDto> messageDtos) throws Exception {
        List<MessageDto> returnMessageDtos = null;
        for (MessageDto messageDto : messageDtos) {
            returnMessageDtos.add(send(messageDto));
        }
        return returnMessageDtos;
    }

    public MessageDto save(MessageDto messageDto) {
        GrapMessageModel grapMessageModel = GrapMessageModel.builder()
                .callback(messageDto.getSendNo())
                .date_client_req(LocalDateTime.now())
                .subject(messageDto.getSubject())
                .template_code(messageDto.getTemplate_code())
                .text(messageDto.getContent())
                .receiverId(messageDto.getEmail())
                .senderSno(messageDto.getSenderSno())
                .build();

        switch (messageDto.getCompany()) {
            case "SEOHAN":
            case "ENP":
                seohanGrapRepository.save(grapMessageModel);
                break;
            case "KAMTEC":
                kamtecGrapRepository.save(grapMessageModel);
                break;
            default:
                break;
        }
        return messageDto;
    }

    public MessageDto makeMessage(MessageDto messageDto) {
        switch (messageDto.getCompany()) {
            case "SEOHAN":
            case "ENP":
                messageDto.setSenderSno(seohanSenderSno);
                break;
            case "KAMTEC":
                messageDto.setSenderSno(kamtecSenderSno);
                break;
            default:
                break;
        }
        messageDto.setText(" [시스템 알림] \n\n" +
                " ■ 시스템 구분 : " + messageDto.getSubject() + "\n" +
                " ■ 발신 일시 : " + LocalDateTime.now().format(dateTimeFormatString) + "\n" +
                " ■ 발신자 : " + messageDto.getSendName() + "\n" +
                " ■ 발신 번호 : " + messageDto.getSendNo() + "\n" +
                " ■ 상세 내용\r\n" + messageDto.getContent() + "\n\n");
        messageDto.setEmail(messageDto.getEmail());
        messageDto.setReceiverId(messageDto.getEmail());
        return messageDto;
    }

}

package com.message.service;

import com.message.domain.GrapMessageModel;
import com.message.dto.GrapDto;
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
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.message.MessageApplication.dateFormatString;

@Service
@Slf4j
public class GrapServiceImpl implements GrapService {
	@Value("${grap.apiUrl}")
	String grapUrl;

	@Value("${grap.apiKey}")
	String CP_KEY_SPEC;
	
	@Value("${grap.seohan.senderSno}")
	String seohanSenderSno ;

	@Value("${grap.kamtec.senderSno}")
	String kamtecSenderSno ;

    @Autowired
    SeohanGrapRepository seohanGrapRepository;
    
    @Autowired
    KamtecGrapRepository kamtecGrapRepository;

	@Override
	public MessageDto send(MessageDto messageDto) throws Exception {
		JSONParser jsonParser = new JSONParser();
		URL url = new URL(grapUrl); // URL 설정

		//		.ReceiverId(messageDto.getReceiverId());
		GrapDto grapDto = new GrapDto();
		grapDto.setSubject(messageDto.getSubject());
		grapDto.setSenderSno(messageDto.getSenderSno());
		grapDto.setEmail(messageDto.getEmail());
		grapDto.setReceiverId(messageDto.getEmail());
		grapDto.setText(messageDto.getContent());

		switch (messageDto.getCompany()) {
			case "SEOHAN":	case "ENP":
				grapDto.setSenderSno(seohanSenderSno);
				break;
			case "KAMTEC":
				grapDto.setSenderSno(kamtecSenderSno);
				break;
			default:
				break;
		}

		try {
			List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();			// RestTemplate 에 MessageConverter 세팅
			converters.add(new FormHttpMessageConverter());
			converters.add(new StringHttpMessageConverter());
			converters.add(new MappingJackson2HttpMessageConverter());

			RestTemplate restTemplate = new RestTemplate();
			restTemplate.setMessageConverters(converters);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("cp-key-spec", CP_KEY_SPEC);

			HttpEntity requestEntity =  new HttpEntity<>( grapDto, headers);
			ResponseEntity response = restTemplate.postForEntity(url.toString(), requestEntity, String.class);

			JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString());
			JSONObject docuObject = (JSONObject) jsonObject.get(0); 			//배열 i번째 요소 불러오고

			if ( jsonObject.get("msg") != null){
				messageDto.setResult(jsonObject.get("msg").toString());
			}
		} catch (Exception e) {
			log.error(e.getStackTrace().toString());
			log.error("message Send failed" + messageDto.toString());
		}

		MessageDto savedMessageDto = save(messageDto);
		return messageDto;
	}

	@Override
	public MessageDto  save(MessageDto messageDto) throws Exception  {

		GrapMessageModel grapMessageModel = makeMessage(messageDto);
		messageDto.setText(messageDto.getContent());

		switch (messageDto.getCompany()) {
			case "SEOHAN":	case "ENP":
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


	 public GrapMessageModel makeMessage(MessageDto messageDto){

		 String nowDateFormatString =  LocalDateTime.now().format(dateFormatString);

		switch (messageDto.getCompany()) {
			case "SEOHAN":	case "ENP": 
				messageDto.setSenderSno(seohanSenderSno);
				break;
			case "KAMTEC": 
				messageDto.setSenderSno(kamtecSenderSno);
				break;
			default:
				break;
		}
		messageDto.setReceiverId(messageDto.getEmail());

		GrapMessageModel grapMessageModel = GrapMessageModel.builder()
				.callback(messageDto.getSendNo())
				.date_client_req(new Timestamp(new Date().getTime()))
				.subject(messageDto.getSubject())
				.template_code(messageDto.getTemplate_code())
				.text(messageDto.getContent())
				.receiverId(messageDto.getEmail())
				.senderSno(messageDto.getSenderSno())
				.build();

		switch(messageDto.getTemplate_code()){
//			case "COM_LONG_03":
//				messageDto.setText(" [시스템 알림] \n\n" +
//						" ■ 시스템 구분 : " + messageDto.getSubject() + "\n" +
//						" ■ 발신 일시 : " + dateFormat.format(date) + "\n" +
//						" ■ 발신자 : " + messageDto.getSendName() + "\n" +
//						" ■ 발신 번호 : " + messageDto.getSendNo() + "\n" +
//						" ■ 상세 내용\r\n" + messageDto.getContent() + "\n\n" );
//				break;
			default:
				messageDto.setText(" [시스템 알림] \n\n" +
						" ■ 시스템 구분 : " + messageDto.getSubject() + "\n" +
						" ■ 발신 일시 : " + nowDateFormatString + "\n" +
						" ■ 발신자 : " + messageDto.getSendName() + "\n" +
						" ■ 발신 번호 : " + messageDto.getSendNo() + "\n" +
						" ■ 상세 내용\r\n" + messageDto.getContent() + "\n\n" );
				break;
		}
		return grapMessageModel;
	}

}

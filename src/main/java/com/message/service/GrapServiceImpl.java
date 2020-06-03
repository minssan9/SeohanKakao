package com.message.service;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.message.dto.MessageDto;
import com.message.kamtec.mapper.KamtecGrapRepository;
import com.message.mssql.domain.GrapMessageModel;
import com.message.seohan.mapper.SeohanGrapRepository; 

@Service
public class GrapServiceImpl implements GrapService {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 
	@Value("${grap.apiUrl}")
	String grapUrl ;

	@Value("${grap.apiKey}")
	String cpKeySpec ;
	
	@Value("${grap.seohan.senderSno}")
	String seohanSenderSno ;

	@Value("${grap.kamtec.senderSno}")
	String kamtecSenderSno ;

    @Autowired
    SeohanGrapRepository seohanGrapRepository;
    
    @Autowired
    KamtecGrapRepository kamtecGrapRepository;

	@Override 
	public GrapMessageModel  save(MessageDto messageDto) throws Exception  {
		String result = "OK";
		JSONParser jsonParser = new JSONParser();
		GrapMessageModel grapMessageModel = makeMessage(messageDto);

		try {	
			URL url = new URL(grapUrl); // URL 설정
			List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			// RestTemplate 에 MessageConverter 세팅		    
		    converters.add(new FormHttpMessageConverter());
		    converters.add(new StringHttpMessageConverter());
			converters.add(new MappingJackson2HttpMessageConverter());

		    restTemplate.setMessageConverters(converters);  
						
			headers.setContentType(MediaType.APPLICATION_JSON); 
			headers.add("cp-key-spec", cpKeySpec);

			HttpEntity<MessageDto> requestEntity =  new  HttpEntity<>( messageDto, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(url.toString(), requestEntity, String.class);
			
			JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString());
			JSONObject docuObject = (JSONObject) jsonObject.get(0); 			//배열 i번째 요소 불러오고

			if ( jsonObject.get("msg") != null){
				grapMessageModel.setReport_code(jsonObject.get("msg").toString());
			}
		} catch (Exception e) {
			System.out.println("message Send failed" + messageDto.toString());
		}

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
		return grapMessageModel;
	}

	 public GrapMessageModel makeMessage(MessageDto messageDto){
		Date date = new Date();
		String result = "OK";
		GrapMessageModel grapMessageModel = new GrapMessageModel();

		switch (messageDto.getCompany()) {
			case "SEOHAN":	case "ENP":
				grapMessageModel.setSenderSno(seohanSenderSno);
				messageDto.setSenderSno(seohanSenderSno);
				break;
			case "KAMTEC":
				grapMessageModel.setSenderSno(kamtecSenderSno);
				messageDto.setSenderSno(kamtecSenderSno);
				break;
			default:
				break;
		}
		messageDto.setReceiverId(messageDto.getEmail());

		grapMessageModel.builder()
				.callback(messageDto.getSendNo())
				.date_client_req(new Date())
				.subject(messageDto.getSubject())
				.template_code(messageDto.getTemplate_code())
				.text(messageDto.getText())
				.receiverId(messageDto.getEmail())
				.text(messageDto.getText())
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
						" ■ 발신 일시 : " + dateFormat.format(date) + "\n" +
						" ■ 발신자 : " + messageDto.getSendName() + "\n" +
						" ■ 발신 번호 : " + messageDto.getSendNo() + "\n" +
						" ■ 상세 내용\r\n" + messageDto.getContent() + "\n\n" );
				break;
		}
		return grapMessageModel;
	}
}

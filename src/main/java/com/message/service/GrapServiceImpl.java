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

import com.message.Dto.MessageDto;
import com.message.grap.mapper.GrapRepository;
import com.message.mssql.domain.GrapMessageModel;

@Service
public class GrapServiceImpl implements GrapService {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 
	@Value("${grap.apiUrl}")
	String baseUrl ;

	@Value("${grap.apiKey}")
	String cpKeySpec ;
	
	@Value("${grap.senderSno}")
	String senderSno ;

    @Autowired
    GrapRepository grapRepository;
    
    @Autowired
    GrapMessageModel grapMessageModel;
    
	@Override 
	public GrapMessageModel  save(MessageDto messageDto) throws Exception  {		
		Date date = new Date();
		JSONParser jsonParser = new JSONParser(); 
		String result = "OK"; 
		
		try {	
			URL url = new URL(baseUrl); // URL 설정  
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
			messageDto.setSenderSno(senderSno);
			messageDto.setReceiverId(messageDto.getEmail());

			switch(messageDto.getTemplate_code()){  
				case "COM_LONG_03":
					messageDto.setText(" [시스템 알림] \n\n" +
						" ■ 시스템 구분 : " + messageDto.getSubject() + "\n" +  
						" ■ 발신 일시 : " + dateFormat.format(date) + "\n" + 
						" ■ 발신자 : " + messageDto.getSendName() + "\n" + 
						" ■ 발신 번호 : " + messageDto.getSendNo() + "\n" + 
						" ■ 상세 내용\r\n" + messageDto.getContent() + "\n\n" );
					break;
				default:
					messageDto.setText(" [시스템 알림] \n\n" +
						" ■ 시스템 구분 : " + messageDto.getSubject() + "\n" +  
						" ■ 발신 일시 : " + dateFormat.format(date) + "\n" + 
						" ■ 상세 내용\r\n" + messageDto.getContent() + "\n\n" );
					break;
			}
			
			grapMessageModel.builder()
			.senderSno(senderSno)
			.receiverId(messageDto.getAccountId() + "@seohan.com")
			.text(messageDto.getText());
			
			grapRepository.save(grapMessageModel);
			
			HttpEntity<MessageDto> requestEntity =  new  HttpEntity<>( messageDto, headers);			 
			ResponseEntity<String> response = restTemplate.postForEntity(url.toString(), requestEntity, String.class);
			
			JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString());
			JSONObject docuObject = (JSONObject) jsonObject.get(0); 			//배열 i번째 요소 불러오고
			
			if ( jsonObject.get("msg") != null){
				grapMessageModel.setReport_code(jsonObject.get("msg").toString());
			}
			
			return grapMessageModel;			 	
		} catch (Exception e) {
			System.out.println("message Send failed" + grapMessageModel.toString());
			return grapMessageModel;
		}
	} 
}

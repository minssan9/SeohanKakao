package com.kakao.Service;

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

import com.kakao.Dto.MessageDto;
import com.kakao.domain.GrapMessageModel;
import com.kakao.kamtec.mapper.GrapKamtecRepository;
import com.kakao.seohan.mapper.GrapSeohanRepository; 
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
    GrapKamtecRepository grapKamtecRepository;
    
    @Autowired
    GrapSeohanRepository grapSeohanRepository;
        
	@Override 
	public MessageDto save(MessageDto messageDto) throws Exception  { 
		String result = "OK";
		GrapMessageModel grapMessageModel = new GrapMessageModel();
		Date date = new Date();

		try {	
			URL url = new URL(baseUrl); // URL 설정  

			// RestTemplate 에 MessageConverter 세팅
		    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		    converters.add(new FormHttpMessageConverter());
		    converters.add(new StringHttpMessageConverter());
			converters.add(new MappingJackson2HttpMessageConverter());
		 
		    RestTemplate restTemplate = new RestTemplate();
		    restTemplate.setMessageConverters(converters);  
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON); 
			headers.add("cp-key-spec", cpKeySpec);

			// messageDto.setAccountId("dhl19923");
			messageDto.setSenderSno(senderSno);
			messageDto.setReceiverId(messageDto.getAccountId() + "@seohan.com");
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

			HttpEntity<MessageDto> requestEntity =  new  HttpEntity<>( messageDto, headers);			 
			ResponseEntity<String> response = restTemplate.postForEntity(url.toString(), requestEntity, String.class); 
			JSONParser jsonParser = new JSONParser(); 
			JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString());
			JSONObject docuObject = (JSONObject) jsonObject.get(0); 			//배열 i번째 요소 불러오고
			
			if ( jsonObject.get("msg") != null){
				result = jsonObject.get("msg").toString();
			}
			messageDto.setResult(result);			
			System.out.println(response);   
		} catch (Exception e) {
			System.out.println("message Send failed" + messageDto.toString());
		}
		
		grapMessageModel.setSubject(messageDto.getSubject());
		grapMessageModel.setContent(messageDto.getContent());
//		grapMessageModel.setReceiver_id(messageDto.getRecipient_num());
		grapMessageModel.setTemplate_code(messageDto.getTemplate_code());  
		 
		grapMessageModel.setPriority("S");
		grapMessageModel.setCallback("");
		grapMessageModel.setReg_date(date);
		grapMessageModel.setDate_client_req(date);
		grapMessageModel.setSenderSno(senderSno);
		grapMessageModel.setMsg_type(1008);
		grapMessageModel.setCountry_code("82");
		grapMessageModel.setMsg_status( "1");
		grapMessageModel.setTemplate_code("COM_LONG_03"); 
		
		switch (messageDto.getCompany()) {
			case "SEOHAN":
				grapSeohanRepository.save(grapMessageModel);
				break;
			case "KAMTEC":
				grapKamtecRepository.save(grapMessageModel);
				break;
		}	

		return messageDto;
	} 
}

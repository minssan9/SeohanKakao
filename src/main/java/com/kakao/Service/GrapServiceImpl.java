package com.kakao.Service;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.kakao.kamtec.mapper.KamtecRepository;

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
    KamtecRepository kakaoRepository;
        
	@Override 
	public MessageDto save(MessageDto messageDto) throws Exception  { 
		String result = "OK";
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

			messageDto.setSenderSno(senderSno);
			messageDto.setReceiverId(messageDto.getAccountId() + "@seohan.com");
			messageDto.setText(messageDto.getContent());

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
			return messageDto;			 	
		} catch (Exception e) {
			System.out.println("message Send failed" + messageDto.toString());
			return messageDto;
		}
	} 
}

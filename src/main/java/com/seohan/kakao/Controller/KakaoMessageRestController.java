package com.seohan.kakao.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.seohan.kakao.Domain.KakaoMessageModel;
import com.seohan.kakao.Dto.MessageDto;
import com.seohan.kakao.Service.KakaoService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/kakao")
@Slf4j 
@RestController
class KakaoMessageRestController {
	@Autowired
	private KakaoService kakaoService;

	@Value("${grap.apiUrl}")
	String baseUrl ;

	@Value("${grap.apiKey}")
	String cpKeySpec ;
	
	@Value("${grap.senderSno}")
	String senderSno ;

	@PostMapping("/save")
	public ResponseEntity<String> createKakaoMessage(@RequestBody MessageDto messageDto ) throws Exception   { 		
 
		if(isNumeric(messageDto.getAccountId().substring(0,1))) {
			// grap massenger http Post  
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
			         
//			logger.info(docuObject.get("msg").toString());
			String result = jsonObject.get("msg").toString();
			System.out.println(response);  
			return new ResponseEntity<String>(result, HttpStatus.OK);	 
		}else		{
			KakaoMessageModel kakaoMessageModel = new KakaoMessageModel();
			kakaoMessageModel.setSubject(messageDto.getSubject());
			kakaoMessageModel.setContent(messageDto.getContent());
			kakaoMessageModel.setRecipient_num(messageDto.getRecipient_num());
			kakaoMessageModel.setTemplate_code(messageDto.getTemplate_code()); 

			KakaoMessageModel KakaoMessageModelCreated = kakaoService.save(kakaoMessageModel );		
			return new ResponseEntity<String>("OK", HttpStatus.OK);	
		}		
		
	}

	public static boolean isNumeric(final String str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
}

package com.kakao.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kakao.Dto.MessageDto;
import com.kakao.domain.KakaoMessageModel;
import com.kakao.kamtec.mapper.KamtecRepository;
import com.kakao.seohan.mapper.SeohanRepository;

@Service
public class KakaoServiceImpl implements KakaoService {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
 
	@Value("${kakaoMessage.apiKey}")
	String senderKey ;

    @Autowired
    KamtecRepository kamtecRepository;
    @Autowired
    SeohanRepository seohanRepository;
        
	@Override 
	public KakaoMessageModel save(MessageDto messageDto) throws Exception  {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		KakaoMessageModel kakaoMessageModel = new KakaoMessageModel();
		try {		

			kakaoMessageModel.setSubject(messageDto.getSubject());
			kakaoMessageModel.setContent(messageDto.getContent());
			kakaoMessageModel.setRecipient_num(messageDto.getRecipient_num());
			kakaoMessageModel.setTemplate_code(messageDto.getTemplate_code());  
			
			Date date = new Date();
			kakaoMessageModel.setMt_refkey(null);
			kakaoMessageModel.setIta_id(" ");
			kakaoMessageModel.setAd_flag("N");
			kakaoMessageModel.setResponse_method("push");
			kakaoMessageModel.setPriority("S");
			kakaoMessageModel.setCallback("");
			kakaoMessageModel.setReg_date(date);
			kakaoMessageModel.setDate_client_req(date);
			kakaoMessageModel.setSender_key(senderKey);
			kakaoMessageModel.setMsg_type(1008);
			kakaoMessageModel.setCountry_code("82");
			kakaoMessageModel.setMsg_status( "1");
			kakaoMessageModel.setTemplate_code("COM_LONG_02");
			
			switch(kakaoMessageModel.getTemplate_code()){
				case "COM_LONG_00":
					kakaoMessageModel.setContent(" [시스템 알림] \n\n" +
						" ■ 시스템 구분 : " + kakaoMessageModel.getSubject() + "\n" +  
						" ■ 발신 일시 : " + dateFormat.format(date) + "\n" + 
						" ■ 상세 내용\r\n" + kakaoMessageModel.getContent() );
					break; 
				case "COM_LONG_03":
					kakaoMessageModel.setContent(" [시스템 알림] \n\n" +
						" ■ 시스템 구분 : " + kakaoMessageModel.getSubject() + "\n" +  
						" ■ 발신 일시 : " + dateFormat.format(date) + "\n" + 
						" ■ 발신자 : " + messageDto.getSendName() + "\n" + 
						" ■ 발신 번호 : " + messageDto.getSendNo() + "\n" + 
						" ■ 상세 내용\r\n" + kakaoMessageModel.getContent() + "\n\n" + 
						" 본 알림톡 대상이 아닌 경우 관련 부서에 연락 바랍니다." + "\n" + 
						" 자세한 내용은 전산시스템에서 확인하시기 바랍니다.");
					break;
				default:
					kakaoMessageModel.setContent(" [시스템 알림] \n\n" +
						" ■ 시스템 구분 : " + kakaoMessageModel.getSubject() + "\n" +  
						" ■ 발신 일시 : " + dateFormat.format(date) + "\n" + 
						" ■ 상세 내용\r\n" + kakaoMessageModel.getContent() + "\n\n" + 
						" 본 알림톡 대상이 아닌 경우 관련 부서에 연락 바랍니다." + "\n" + 
						" 자세한 내용은 전산시스템에서 확인하시기 바랍니다.");
					break;
			} 

			switch (messageDto.getCompany()) {
				case "SEOHAN":
					seohanRepository.save(kakaoMessageModel);
					break;
				case "KAMTEC":
					kamtecRepository.save(kakaoMessageModel);
					break;
				default:
					break;
			}	
				
		} catch (Exception e) {
			System.out.println("message Send failed" + kakaoMessageModel.toString());
			return kakaoMessageModel;
		}
		return kakaoMessageModel;
	} 
}

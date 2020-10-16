package com.message.service;

import com.message.domain.KakaoMessageModel;
import com.message.dto.KakaoDto;
import com.message.dto.MessageDto;
import com.message.mapper.kamtec.KamtecKakaoRepository;
import com.message.mapper.seohan.SeohanKakaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.message.MessageApplication.dateFormatString;
import static com.message.MessageApplication.dateTimeFormatString;

@Service
public class KakaoServiceImpl implements KakaoService {
	@Value("${kakaoMessage.seohan.apiKey}")
	String senderKeySeohan ;
	
	@Value("${kakaoMessage.kamtec.apiKey}")
	String senderKeyKamtec ;

    @Autowired
    KamtecKakaoRepository kamtecRepository;
    @Autowired
    SeohanKakaoRepository seohanRepository;
        
	@Override 
	public KakaoMessageModel save(MessageDto messageDto) throws Exception  {
		KakaoMessageModel kakaoMessageModel = new KakaoMessageModel();
		LocalDateTime date = LocalDateTime.now();
		String nowFormatDate  = LocalDateTime.now().format(dateFormatString);

		try {
			kakaoMessageModel.setSubject(messageDto.getSubject());
			kakaoMessageModel.setContent(messageDto.getContent());
			kakaoMessageModel.setRecipient_num(messageDto.getRecipient_num());
			kakaoMessageModel.setTemplate_code(messageDto.getTemplate_code());


			kakaoMessageModel.setMt_refkey(null);
			kakaoMessageModel.setIta_id(" ");
			kakaoMessageModel.setAd_flag("N");
			kakaoMessageModel.setResponse_method("push");
			kakaoMessageModel.setPriority("S");
			kakaoMessageModel.setCallback("");
			kakaoMessageModel.setReg_date(date);
			kakaoMessageModel.setDate_client_req(date);
			kakaoMessageModel.setMsg_type(1008);
			kakaoMessageModel.setCountry_code("82");
			kakaoMessageModel.setMsg_status( "1");
			kakaoMessageModel.setTemplate_code("COM_LONG_03");
			
			switch(kakaoMessageModel.getTemplate_code()){
				case "COM_LONG_00":
					kakaoMessageModel.setContent(" [시스템 알림] \n\n" +
						" ■ 시스템 구분 : " + kakaoMessageModel.getSubject() + "\n" +  
						" ■ 발신 일시 : " + LocalDateTime.now().format(dateTimeFormatString) + "\n" +
						" ■ 상세 내용\r\n" + kakaoMessageModel.getContent() );
					break; 
				case "COM_LONG_03": 
					kakaoMessageModel.setContent(" [시스템 발송] \n\n" +
						" ■ 시스템 구분 : " + kakaoMessageModel.getSubject() + "\n" +  
						" ■ 발신 일시 : " + LocalDateTime.now().format(dateTimeFormatString) + "\n" +
						" ■ 발신자 : " + messageDto.getSendName() + "\n" + 
						" ■ 발신 번호 : " + messageDto.getSendNo() + "\n" + 
						" ■ 상세 내용\r\n" + kakaoMessageModel.getContent() + "\n\n" + 
						" 본 알림톡 대상이 아닌 경우 관련 부서에 연락 바랍니다." + "\n" + 
						" 자세한 내용은 전산시스템에서 확인하시기 바랍니다.");
					break;
				default:
					kakaoMessageModel.setContent(" [시스템 알림] \n\n" +
						" ■ 시스템 구분 : " + kakaoMessageModel.getSubject() + "\n" +  
						" ■ 발신 일시 : " + LocalDateTime.now().format(dateTimeFormatString) + "\n" +
						" ■ 상세 내용\r\n" + kakaoMessageModel.getContent() + "\n\n" + 
						" 본 알림톡 대상이 아닌 경우 관련 부서에 연락 바랍니다." + "\n" + 
						" 자세한 내용은 전산시스템에서 확인하시기 바랍니다.");
					break;
			} 

			switch (messageDto.getCompany()) {
				case "SEOHAN":
				case "ENP":
					kakaoMessageModel.setSender_key(senderKeySeohan);
					seohanRepository.save(kakaoMessageModel);
					break;
				case "KAMTEC":
					kakaoMessageModel.setSender_key(senderKeyKamtec);
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

	@Override
	public KakaoMessageModel send(KakaoDto  kakaoDto) throws Exception  {
		KakaoMessageModel kakaoMessageModel = new KakaoMessageModel();
		LocalDateTime date = LocalDateTime.now();

		try {
			kakaoMessageModel.setSubject(kakaoDto.getSubject());
			kakaoMessageModel.setContent(kakaoDto.getContent());
			kakaoMessageModel.setRecipient_num(kakaoDto.getRecipient_num());
			kakaoMessageModel.setTemplate_code(kakaoDto.getTemplate_code());

			kakaoMessageModel.setMt_refkey(null);
			kakaoMessageModel.setIta_id(" ");
			kakaoMessageModel.setAd_flag("N");
			kakaoMessageModel.setResponse_method("push");
			kakaoMessageModel.setPriority("S");
			kakaoMessageModel.setCallback("");
			kakaoMessageModel.setReg_date(date);
			kakaoMessageModel.setDate_client_req(date);
			kakaoMessageModel.setMsg_type(1008);
			kakaoMessageModel.setCountry_code("82");
			kakaoMessageModel.setMsg_status( "1");
			kakaoMessageModel.setTemplate_code("COM_LONG_03");

			switch(kakaoMessageModel.getTemplate_code()){
				case "COM_LONG_00":
					kakaoMessageModel.setContent(" [시스템 알림] \n\n" +
							" ■ 시스템 구분 : " + kakaoMessageModel.getSubject() + "\n" +
							" ■ 발신 일시 : " + LocalDateTime.now().format(dateTimeFormatString) + "\n" +
							" ■ 상세 내용\r\n" + kakaoMessageModel.getContent() );
					break;
				case "COM_LONG_03":
					kakaoMessageModel.setContent(" [시스템 발송] \n\n" +
							" ■ 시스템 구분 : " + kakaoMessageModel.getSubject() + "\n" +
							" ■ 발신 일시 : " + LocalDateTime.now().format(dateTimeFormatString) + "\n" +
							" ■ 발신자 : " + kakaoDto.getSendName() + "\n" +
							" ■ 발신 번호 : " + kakaoDto.getSendNo() + "\n" +
							" ■ 상세 내용\r\n" + kakaoMessageModel.getContent() + "\n\n" +
							" 본 알림톡 대상이 아닌 경우 관련 부서에 연락 바랍니다." + "\n" +
							" 자세한 내용은 전산시스템에서 확인하시기 바랍니다.");
					break;
				default:
					kakaoMessageModel.setContent(" [시스템 알림] \n\n" +
							" ■ 시스템 구분 : " + kakaoMessageModel.getSubject() + "\n" +
							" ■ 발신 일시 : " + LocalDateTime.now().format(dateTimeFormatString) + "\n" +
							" ■ 상세 내용\r\n" + kakaoMessageModel.getContent() + "\n\n" +
							" 본 알림톡 대상이 아닌 경우 관련 부서에 연락 바랍니다." + "\n" +
							" 자세한 내용은 전산시스템에서 확인하시기 바랍니다.");
					break;
			}

			switch (kakaoDto.getCompany()) {
				case "SEOHAN":
				case "ENP":
					kakaoMessageModel.setSender_key(senderKeySeohan);
					seohanRepository.save(kakaoMessageModel);
					break;
				case "KAMTEC":
					kakaoMessageModel.setSender_key(senderKeyKamtec);
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

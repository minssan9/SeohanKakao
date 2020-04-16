package com.seohan.kakao.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.seohan.kakao.Domain.KakaoMessageModel;
import com.seohan.kakao.Mapper.KakaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KakaoServiceImpl implements KakaoService {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
 
    @Autowired
    KakaoRepository kakaoRepository;
        
	@Override 
	public KakaoMessageModel save(KakaoMessageModel kakaoMessageModel) throws Exception  {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {			
			Date date = new Date();
			kakaoMessageModel.setMt_refkey(null);
			kakaoMessageModel.setIta_id(" ");
			kakaoMessageModel.setAd_flag("N");
			kakaoMessageModel.setResponse_method("push");
			kakaoMessageModel.setPriority("S");
			kakaoMessageModel.setCallback("");
			kakaoMessageModel.setReg_date(date);
			kakaoMessageModel.setDate_client_req(date);
			kakaoMessageModel.setSender_key("c0eaebb438dbc16a5a99e33ff827f1b20c4aeb31");
			kakaoMessageModel.setMsg_type(1008);
			kakaoMessageModel.setCountry_code("82");
			kakaoMessageModel.setMsg_status( "1");
			kakaoMessageModel.setTemplate_code("COM_LONG_02");
//			kakaoMessageModel.setSubject("");
			kakaoMessageModel.setContent(" [시스템 알림] \n\n" +
					" ■ 시스템 구분 : " + kakaoMessageModel.getSubject() + "\n" +  
					" ■ 발신 일시 : " + dateFormat.format(date) + "\n" + 
					" ■ 상세 내용\r\n" + kakaoMessageModel.getContent() + "\n\n" + 
					" 본 알림톡 대상이 아닌 경우 관련 부서에 연락 바랍니다." + "\n" + 
					" 자세한 내용은 전산시스템에서 확인하시기 바랍니다.");
			
			// kakaoRepository.sendMessage(kakaoMessageModel.getTemplate_code(), kakaoMessageModel.getRecipient_num(), kakaoMessageModel.getContent() );
			kakaoRepository.save(kakaoMessageModel);	
		} catch (Exception e) {
			System.out.println("message Send failed");
			return kakaoMessageModel;
		}
		return kakaoMessageModel;
	} 
}

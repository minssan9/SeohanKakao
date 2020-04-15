package com.seohan.kakao;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seohan.kakao.Domain.KakaoMessageModel;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KakaoMessageApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	 	
	@Test 
	public void requestTest () throws Exception {		
		KakaoMessageModel kakaoMessageModel = new KakaoMessageModel();
			kakaoMessageModel.builder().content("[사내 자동 발송]  \r\n\r\n" +
                    " ■ 내용 \r\n " + "CONTENT" + "\r\n" + 
                    " ■ 시스템 구분 : " + "title" + "\r\n" + 
                    " ■ 발신자 : " + "senderName" + "\r\n" + 
                    " ■ 발신 번호 : " + "sendNo" + "\r\n" + 
                    " ■ 발신 일시 : " + "yyyy-MM-dd hh:nn" + "\r\n" + 
                    " 본 알림톡 대상이 아닌 경우 관련 부서에 연락 바랍니다." + "\r\n" + 
                    " 자세한 내용은 전산시스템에서 확인하시기 바랍니다.")
			.msg_status("1")
			.recipient_num("01067766160")
			.msg_type(1008)
			.sender_key("c0eaebb438dbc16a5a99e33ff827f1b20c4aeb31")
			.template_code("COM_MMS_00")
			.country_code("82");
 			
		kakaoMessageModel.setContent("[사내 자동 발송]  \r\n\r\n" +
                    " ■ 내용 \r\n " + "CONTENT" + "\r\n" + 
                    " ■ 시스템 구분 : " + "title" + "\r\n" + 
                    " ■ 발신자 : " + "senderName" + "\r\n" + 
                    " ■ 발신 번호 : " + "sendNo" + "\r\n" + 
                    " ■ 발신 일시 : " + "yyyy-MM-dd hh:nn" + "\r\n" + 
                    " 본 알림톡 대상이 아닌 경우 관련 부서에 연락 바랍니다." + "\r\n" + 
                    " 자세한 내용은 전산시스템에서 확인하시기 바랍니다.");
		kakaoMessageModel.setMsg_status("1");
		kakaoMessageModel.setRecipient_num("01067766160");
		kakaoMessageModel.setMsg_type(1008);
		kakaoMessageModel.setSender_key("c0eaebb438dbc16a5a99e33ff827f1b20c4aeb31");
		kakaoMessageModel.setTemplate_code("COM_MMS_00");
		kakaoMessageModel.setCountry_code("82");
		kakaoMessageModel.setMt_refkey("P150149");

		mockMvc.perform(MockMvcRequestBuilders.post("/kakao/save/") 
	            .content(asJsonString(kakaoMessageModel))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk()); 
	}

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

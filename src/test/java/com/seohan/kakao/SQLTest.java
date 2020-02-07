package com.seohan.kakao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.seohan.kakao.Domain.KakaoMessageModel;
import com.seohan.kakao.Mapper.KakaoRepository;

public class SQLTest {

@SpringBootTest
public class SeohanKakaoApplicationTests {
 

    @Autowired
    private KakaoRepository kakaoRepository;
	
    @Test
    public void testFetchData(){
        /*Test data retrieval*/
//        KakaoMessageModel kakaoModel = kakaoRepository.findAll();
//        assertNotNull(kakaoModel); 
    }
}
}

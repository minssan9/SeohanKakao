package com.message.mapper.kamtec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.message.domain.KakaoMessageModel;

@Repository
public interface KamtecKakaoRepository extends JpaRepository<KakaoMessageModel, Long> {	 

	@Query(value="insert into ita_talk_tran " + 
				"(date_client_req, content, msg_status, recipient_num, msg_type, sender_key, template_code, country_code)" + 
				"values(getdate(), :content, '1', :recipient_num,  '1008',  '4d2238c23c1663f71f6f5d1a671aa315d5b457c7', :template_code, '82')"	, 
				nativeQuery = true)
	public void sendMessage(@Param("template_code") String template_code,
							 @Param("recipient_num") String recipient_num,
							 @Param("content") String content ) throws Exception;	
 
}

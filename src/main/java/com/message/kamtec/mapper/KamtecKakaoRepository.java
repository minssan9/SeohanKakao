package com.message.kamtec.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.message.mssql.domain.KakaoMessageModel; 

@Repository
public interface KamtecKakaoRepository extends JpaRepository<KakaoMessageModel, Long> {	 

	@Query(value="insert into ita_talk_tran " + 
				"(date_client_req, content, msg_status, recipient_num, msg_type, sender_key, template_code, country_code)" + 
				"values(getdate(), :content, '1', :recipient_num,  '1008',  'c0eaebb438dbc16a5a99e33ff827f1b20c4aeb31', :template_code, '82')"	, 
				nativeQuery = true)
	public void sendMessage(@Param("template_code") String template_code,
							 @Param("recipient_num") String recipient_num,
							 @Param("content") String content ) throws Exception;	
 
}

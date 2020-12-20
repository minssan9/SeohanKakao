package com.message.domain;

import com.message.dto.MessageDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "ITA_TALK_TRAN")
public class KakaoMessageModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mt_pr;
	private String mt_refkey;
	private String priority;
	private LocalDateTime date_client_req;
	private String subject;
	private String content;
	private String callback;
	private String msg_status;
	private String recipient_num;
	private LocalDateTime date_mt_sent;
	private LocalDateTime date_rslt;
	private LocalDateTime date_mt_report;
	private String report_code;
	private String rs_id;
	private String country_code;
	private int msg_type;
	private String crypto_yn;
	private String ita_id;
	private LocalDateTime reg_date;
	private String sender_key;
	private String template_code;
	private String response_method;
	private String ad_flag = "";
	private String kko_btn_type;
	private String kko_btn_info;
	private String img_url;
	private String img_link;
	private String etc_text_1;
	private String etc_text_2;
	private String etc_text_3;
	private int etc_num_1;
	private int etc_num_2;
	private int etc_num_3;
	private LocalDateTime etc_date_1;

	public KakaoMessageModel(MessageDto messageDto, String senderKey) {
		LocalDateTime date = LocalDateTime.now();

		this.subject = messageDto.getSubject();
		this.content = messageDto.getContent();
		this.recipient_num = messageDto.getRecipient_num();
		this.template_code = messageDto.getTemplate_code();
		this.mt_refkey = senderKey;
		this.ita_id = " ";
		this.ad_flag = "N";
		this.response_method = "push";
		this.priority = "S";
		this.callback = "";
		this.reg_date = date;
		this.date_client_req = date;
		this.msg_type = 1008;
		this.country_code = "82";
		this.msg_status =  "1";
		this.template_code = "COM_LONG_03";
	}
}
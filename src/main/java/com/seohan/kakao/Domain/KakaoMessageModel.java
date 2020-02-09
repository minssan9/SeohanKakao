package com.seohan.kakao.Domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ITA_TALK_TRAN")
public class KakaoMessageModel {
	@Id
	@GeneratedValue
	private int mt_pr;
	private String mt_refkey;
	private String priority;
	private Date date_client_req;
	private String subject;
	private String content;
	private String callback;
	private String msg_status;
	private String recipient_num;
	private Date date_mt_sent;
	private Date date_rslt;
	private Date date_mt_report;
	private String report_code;
	private String rs_id;
	private String country_code;
	private int msg_type;
	private String crypto_yn;
	private String ita_id;
	private Date reg_date;
	private String sender_key;
	private String template_code;
	private String response_method;
	private String ad_flag;
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
	private Date etc_date_1;

}
package com.message.mssql.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GRAP_TALK")
public class GrapMessageModel {  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mt_pr;
	private String priority="";
	private Date date_client_req;
	private String subject;
	private String text;
	private String callback="";
	private String msg_status;
	private String receiverId;
	private String report_code;
	private String country_code;
	private int msg_type;
	private Date reg_date;
	private String senderSno;
	private String cpKeySpec; 
	private String template_code;
	
	private String response;	
}
package com.message.mssql.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GRAP_TALK")
public class GrapMessageModel {  
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mt_pr;
	private String callback="";
	private String country_code="";
	private Timestamp date_client_req;	
	private String msg_status="";
	@NotNull
	private int msg_type;
	private String priority="";
	
	private String receiverId="";
	private Date reg_date;
	private String report_code="";	
	private String senderSno="";
	private String subject="";
	
	private String template_code="";

	@Column(length = 4000)
	private String text="";		
	
	private String cpKeySpec=""; 	
	private String response="";	
}
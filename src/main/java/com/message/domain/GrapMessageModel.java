package com.message.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "GRAP_TALK")
public class GrapMessageModel {  
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mt_pr;
	private String callback;
	private String country_code;
	private LocalDateTime date_client_req;
	private String msg_status;
	@NotNull
	private int msg_type;
	private String priority;
	
	private String receiverId;
	private LocalDateTime reg_date;
	private String report_code;	
	private String senderSno;
	private String subject;
	
	private String template_code;

	@Column(length = 4000)
	private String text;		
	
	private String cpKeySpec; 	
	private String response;	
}
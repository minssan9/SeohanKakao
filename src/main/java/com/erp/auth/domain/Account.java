package com.erp.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
@Table(name = "ORG_USER", schema = "PCMLIB")
public class Account implements Serializable {

	@Id
	@Column(name="EMPID")
	private String accountId;

	private String companyCode;

	private String loginid;
	private String alias;
	private String email;
	private String maindeptcode;
	private String createddt;
	private String legacyexchangedn;
	private String displayname;
	private String displayyn;
	private String roletype;
	private String dutycode;
	private String jobcode;
	private String rankcode;
	private String cellphone;
	private String faxnumber;
	private String extensionnumber;
	private String locationcode;
	private String teamchiefyn;
	private String dateofbirth;
	private String ifyn;
	private String messangerifyn;
	private String phone1;
	private String phone2;
	private String culture;
	private String org_cd;
	private String regid;
	private String regdt;
	private String updid;
	private String upddt;
	private String sapid;
	private String sapmngyn;
	private String kostl;
	private String valid_yn;
	private String jobdesc;
	private String dpartner;
	private String onsite;
	private String sitecompany;
}
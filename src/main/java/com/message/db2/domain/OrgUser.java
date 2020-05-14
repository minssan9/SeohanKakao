package com.message.db2.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrgUser.class)
@Table(name="ORG_USER", schema="PCMLIB")
public class OrgUser implements Serializable{
	@Id
	private String COMPANYCODE	;
	@Id
	private String EMPID	;
	private String LOGINID	;
	private String ALIAS	;
	private String EMAIL	;
	private String MAINDEPTCODE	;
	private String CREATEDDT	;
	private String LEGACYEXCHANGEDN	;
	private String DISPLAYNAME	;
	private String DISPLAYYN	;
	private String ROLETYPE	;
	private String DUTYCODE	;
	private String JOBCODE	;
	private String RANKCODE	;
	private String CELLPHONE	;
	private String FAXNUMBER	;
	private String EXTENSIONNUMBER	;
	private String LOCATIONCODE	;
	private String TEAMCHIEFYN	;
	private String DATEOFBIRTH	;
	private String IFYN	;
	private String MESSANGERIFYN	;
	private String PHONE1	;
	private String PHONE2	;
	private String CULTURE	;
	private String ORG_CD	;
	private String REGID	;
	private Date REGDT	;
	private String UPDID	;
	private Date UPDDT	;
	private String SAPID	;
	private String SAPMNGYN	;
	private String KOSTL	;
	private String VALID_YN	;
	private String JOBDESC	;
	private String DPARTNER	;
	private String ONSITE	;
	private String SITECOMPANY	;

}

package com.kakao.seohan.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.domain.GrapMessageModel; 

@Repository
public interface GrapSeohanRepository extends JpaRepository<GrapMessageModel, Long> {	  
	  @Transactional
	  @Procedure(procedureName = "sp_grap_talk_log_create")
	  boolean makeLogTable(@Param("@p_log_table") String p_log_table);
}

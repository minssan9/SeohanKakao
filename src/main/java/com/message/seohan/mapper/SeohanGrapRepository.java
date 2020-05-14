package com.message.seohan.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.message.mssql.domain.GrapMessageModel; 

@Repository
public interface SeohanGrapRepository extends JpaRepository<GrapMessageModel, Long> {	 

							 
}

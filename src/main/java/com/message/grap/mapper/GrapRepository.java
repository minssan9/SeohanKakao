package com.message.grap.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.message.mssql.domain.GrapMessageModel; 

@Repository
public interface GrapRepository extends JpaRepository<GrapMessageModel, Long> {	 

							 
}

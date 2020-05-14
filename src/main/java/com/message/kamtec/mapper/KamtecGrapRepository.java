package com.message.kamtec.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.message.mssql.domain.GrapMessageModel; 

@Repository
public interface KamtecGrapRepository extends JpaRepository<GrapMessageModel, Long> {	 

							 
}

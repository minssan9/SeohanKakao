package com.kakao.kamtec.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kakao.domain.GrapMessageModel; 

@Repository
public interface GrapKamtecRepository extends JpaRepository<GrapMessageModel, Long> {	 
}

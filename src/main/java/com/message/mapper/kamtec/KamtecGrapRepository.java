package com.message.mapper.kamtec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.message.domain.GrapMessageModel;

@Repository
public interface KamtecGrapRepository extends JpaRepository<GrapMessageModel, Long> {	 

							 
}

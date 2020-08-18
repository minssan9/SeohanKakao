package com.message.mapper.seohan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.message.domain.GrapMessageModel;

@Repository
public interface SeohanGrapRepository extends JpaRepository<GrapMessageModel, Long> {	 

							 
}

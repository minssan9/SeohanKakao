package com.message.mapper.seohan;

import com.message.domain.KakaoMessageModel;
import com.message.mapper.KakaoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SeohanKakaoRepository implements KakaoRepository {
 	
	@PersistenceContext(unitName = "seohanEntityManager")
	private EntityManager em;

	public void save(KakaoMessageModel kakaoMessageModel){
		if (kakaoMessageModel.getIta_id() == null){
			em.persist(kakaoMessageModel);
		}
	}

}

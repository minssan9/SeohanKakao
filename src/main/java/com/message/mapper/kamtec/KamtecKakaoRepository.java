package com.message.mapper.kamtec;

import com.message.mapper.KakaoRepository;
import org.springframework.stereotype.Repository;

import com.message.domain.KakaoMessageModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class KamtecKakaoRepository implements KakaoRepository {
    @PersistenceContext(unitName = "kamtecEntityManager")
    private EntityManager em;

    public void save(KakaoMessageModel kakaoMessageModel){
        if (kakaoMessageModel.getIta_id() == null){
            em.persist(kakaoMessageModel);
        }
    }
}

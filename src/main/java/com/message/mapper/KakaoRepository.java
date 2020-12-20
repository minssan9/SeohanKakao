package com.message.mapper;

import com.message.domain.KakaoMessageModel;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface KakaoRepository {
    void save(KakaoMessageModel kakaoMessageModel);
}

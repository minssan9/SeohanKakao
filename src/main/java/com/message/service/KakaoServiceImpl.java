package com.message.service;

import com.message.domain.KakaoMessageModel;
import com.message.dto.MessageDto;
import com.message.dto.RepositoryType;
import com.message.mapper.KakaoRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.message.util.CommonUtil.dateTimeFormatString;

@Service
public class KakaoServiceImpl implements MessageService {
    @Value("${kakaoMessage.seohan.apiKey}")
    String senderKeySeohan;
    @Value("${kakaoMessage.kamtec.apiKey}")
    String senderKeyKamtec;
    @Autowired
    KakaoRepository kakaoRepository;
    @Autowired
    BeanFactory beanFactory;


    @Override
    public List<MessageDto> send(List<MessageDto> messageDtos) {
        return null;
    }


    @Override
    public MessageDto send(MessageDto messageDto) {
        String senderKey = "";

        KakaoMessageModel kakaoMessageModel = makeMessageModel(messageDto, senderKey);

        RepositoryType repositoryType = RepositoryType.getRepositoryType(messageDto.getCompany());
        ((KakaoRepository)beanFactory.getBean(repositoryType.getRepoType())).save(kakaoMessageModel);

        return messageDto;
    }

    private KakaoMessageModel makeMessageModel(MessageDto messageDto, String senderKey) {
        switch (messageDto.getTemplate_code()) {
            case "COM_LONG_00":
                messageDto.setText(" [시스템 알림] \n\n" +
                        " ■ 시스템 구분 : " + messageDto.getSubject() + "\n" +
                        " ■ 발신 일시 : " + LocalDateTime.now().format(dateTimeFormatString) + "\n" +
                        " ■ 상세 내용\r\n" + messageDto.getContent());
                break;
            case "COM_LONG_03":
                messageDto.setText(" [시스템 발송] \n\n" +
                        " ■ 시스템 구분 : " + messageDto.getSubject() + "\n" +
                        " ■ 발신 일시 : " + LocalDateTime.now().format(dateTimeFormatString) + "\n" +
                        " ■ 발신자 : " + messageDto.getSendName() + "\n" +
                        " ■ 발신 번호 : " + messageDto.getSendNo() + "\n" +
                        " ■ 상세 내용\r\n" + messageDto.getContent() + "\n\n" +
                        " 본 알림톡 대상이 아닌 경우 관련 부서에 연락 바랍니다." + "\n" +
                        " 자세한 내용은 전산시스템에서 확인하시기 바랍니다.");
                break;
            default:
                messageDto.setText(" [시스템 알림] \n\n" +
                        " ■ 시스템 구분 : " + messageDto.getSubject() + "\n" +
                        " ■ 발신 일시 : " + LocalDateTime.now().format(dateTimeFormatString) + "\n" +
                        " ■ 상세 내용\r\n" + messageDto.getContent() + "\n\n" +
                        " 본 알림톡 대상이 아닌 경우 관련 부서에 연락 바랍니다." + "\n" +
                        " 자세한 내용은 전산시스템에서 확인하시기 바랍니다.");
                break;
        }

        switch (messageDto.getCompany()) {
            case "SEOHAN":
            case "ENP":
                senderKey = (senderKeySeohan);
                break;
            case "KAMTEC":
                senderKey = (senderKeyKamtec);
                break;
            default:
                break;
        }
        return new KakaoMessageModel(messageDto, senderKey);
    }
}

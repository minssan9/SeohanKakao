package com.message.test;

import com.message.domain.OrgUser;
import com.message.mapper.db2.OrgUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgUserTest {

    @Autowired
    private OrgUserRepository orgUserRepository;

    @Test
    public void getEmail(){
        OrgUser orgUser = orgUserRepository.findByEmpid("4150149");

//        Assert.assertEquals("4150149@seohan.com", orgUser.getEmail());
        Assert.assertNotNull( orgUser.getEmail());
    }
}

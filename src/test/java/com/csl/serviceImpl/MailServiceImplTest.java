package com.csl.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by csl on 2017/4/14.
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class MailServiceImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private MailServiceImpl mailService;

    @Test
    public void sendMail1() {
        String str = this.mailService.sendValidateCode("1936902534@qq.com");
    }
}

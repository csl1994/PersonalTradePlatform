package com.csl.serviceImpl;

import com.csl.domain.FinalValue;
import com.csl.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by csl on 2017/4/13.
 */
@Service
public class MailServiceImpl implements IMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public String sendValidateCode(String acceptEmail) {
        SimpleMailMessage mail = new SimpleMailMessage();
        String validateCode = "";
        for (int i = 0; i < FinalValue.VALIDATE_LENGTH; i++) {
            validateCode += new Random().nextInt(10);
        }
        mail.setTo(acceptEmail);
        mail.setFrom(FinalValue.SENDER);
        mail.setSubject(FinalValue.VALIDATE_MAIL_SUBJECT);
        mail.setText(validateCode);
        javaMailSender.send(mail);
        return validateCode;
    }
}

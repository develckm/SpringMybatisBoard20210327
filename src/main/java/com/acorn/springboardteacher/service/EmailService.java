package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.EmailDto;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class EmailService {
    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void send(EmailDto emailDto){
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();//메세지 보내기 위한 준비
        try {
            //본문 제목 작성을 도와줌
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(emailDto.getTo());
            mimeMessageHelper.setSubject(emailDto.getSubject());
            mimeMessageHelper.setText(emailDto.getMessage(),true);//내용으로 html
            javaMailSender.send(mimeMessage);
            log.info(emailDto.getTo()+"에게 이메일 보내기");
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }
}

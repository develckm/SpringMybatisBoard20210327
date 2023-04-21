package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service  < Component : 여러 dao 를 실행하고 @Transaction 을 정의할 때 사용 명시적!
@Component
public class EmailService {
    private JavaMailSender javaMailSender;
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendMail(EmailDto emailDto) throws MessagingException {
        MimeMessage mimeMessage= javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(emailDto.getTo());
        mimeMessageHelper.setSubject(emailDto.getTitle());
        mimeMessageHelper.setText(emailDto.getMessage(),true);
        javaMailSender.send(mimeMessage);
    }

}

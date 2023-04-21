package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.EmailDto;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmailServiceTest {
    @Autowired
    private EmailService emailService;
    @Test
    void sendMail() throws MessagingException {
        EmailDto emailDto=new EmailDto();
        emailDto.setTo("develckm@gmail.com");
        emailDto.setTitle("이메일 보내기 테스트");
        emailDto.setMessage("<p>내용 입니다.<b>최경민~!!!</b></p>");
        emailService.sendMail(emailDto);
    }
}
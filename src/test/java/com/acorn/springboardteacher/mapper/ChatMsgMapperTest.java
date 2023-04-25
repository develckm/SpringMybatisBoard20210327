package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.ChatMsgDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChatMsgMapperTest {
    @Autowired
    private ChatMsgMapper chatMsgMapper;
    @Test
    void insertOne() {
        ChatMsgDto chatMsgDto=new ChatMsgDto();
        chatMsgDto.setCrId(1);
        chatMsgDto.setNickname("윤우상");
        chatMsgDto.setUId("user07");
        chatMsgDto.setContent("오늘 날씨가 추워~");
        chatMsgDto.setStatus(ChatMsgDto.Status.CHAT);
        chatMsgMapper.insertOne(chatMsgDto);
    }

    @Test
    void findByCrId() {
        chatMsgMapper.findByCrId(1);
    }
    @Test
    void findByCrIdAndGraterThanPostTime() {
        chatMsgMapper.findByCrIdAndGraterThanPostTime(1,"2023-04-25 15:25:00");
    }
}
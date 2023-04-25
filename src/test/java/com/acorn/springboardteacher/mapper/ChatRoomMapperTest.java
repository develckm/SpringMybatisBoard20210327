package com.acorn.springboardteacher.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChatRoomMapperTest {
    @Autowired
    private ChatRoomMapper chatRoomMapper;
    @Test
    void findAll() {
        chatRoomMapper.findAll();
    }
}
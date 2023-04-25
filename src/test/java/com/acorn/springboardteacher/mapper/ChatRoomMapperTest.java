package com.acorn.springboardteacher.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChatRoomMapperTest {
    @Autowired
    ChatRoomMapper chatRoomMapper;
    @Test
    void insertOne() {
    }

    @Test
    void findAll() {
        chatRoomMapper.findAll();
    }
}
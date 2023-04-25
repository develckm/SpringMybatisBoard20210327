package com.acorn.springboardteacher.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CharRoomMessageMapperTest {
    @Autowired
    ChatMessageMapper charRoomMessageMapper;
    @Test
    void findByCrIdOrderByPostTime() {
        charRoomMessageMapper.findByCrIdOrderByPostTime(1);
    }

    @Test
    void findByCrIdAndPostGraterThenOrderByPostTime() {
        charRoomMessageMapper.findByCrIdAndPostGraterThenOrderByPostTime(1,"2022-01-05 16:00:00.0");
    }

    @Test
    void insertOne() {
    }
}
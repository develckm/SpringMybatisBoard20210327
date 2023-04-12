package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.BoardHashTagDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardHashTagMapperTest {
    @Autowired
    private BoardHashTagMapper boardHashTagMapper;
    static BoardHashTagDto boardHashTagDto;
    @Test
    @Order(1)
    void insertOne() {
        boardHashTagDto=new BoardHashTagDto();
        boardHashTagDto.setTag("에이콘아카데미");
        boardHashTagDto.setBId(1);
        int insert= boardHashTagMapper.insertOne(boardHashTagDto);
        assertTrue(insert>0);
    }
    @Test
    @Order(2)
    void findByBId() {
        List<BoardHashTagDto> tags = boardHashTagMapper.findByBId(1);
        assertNotNull(tags);
    }

    @Test
    @Order(3)
    void countByTag() {
        int cnt=boardHashTagMapper.countByTag("에이콘아카데미");
        assertTrue(cnt>0);
    }
    @Test
    @Order(4)
    void deleteOne() {
        int del= boardHashTagMapper.deleteOne(boardHashTagDto);
        assertTrue(del>0);
    }
}
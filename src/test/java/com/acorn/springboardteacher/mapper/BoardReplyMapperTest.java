package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.BoardReplyDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest //junit 테스를할때 스프링부트가 같이 실행되면서 의존성 주입을 받을 수있다.
class BoardReplyMapperTest {
    @Autowired
    private BoardReplyMapper boardReplyMapper;
    @Test
    void findByBIdAndParentBrIdIsNull() {
        List<BoardReplyDto> boardReplies=boardReplyMapper.findByBIdAndParentBrIdIsNull(6);
        System.out.println("boardReplies = " + boardReplies);
        Assertions.assertNotNull(boardReplies);
    }

    @Test
    void findByParentBrId() {
    }

    @Test
    void findByBrId() {
    }

    @Test
    void insertOne() {
    }

    @Test
    void updateOne() {
    }

    @Test
    void deleteOne() {
    }
}
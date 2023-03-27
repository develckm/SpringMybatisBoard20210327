package com.acorn.springboardteacher.mapper;
import com.acorn.springboardteacher.dto.BoardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class BoardMapperTest {
    @Autowired
    private BoardMapper boardMapper;

    @Test
    void findAll() {
        List<BoardDto> boardList=boardMapper.findAll();
        System.out.println("boardList = " + boardList);
    }

    @Test
    void findByBId() {
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

    @Test
    void updateStatusByBId() {
    }
}
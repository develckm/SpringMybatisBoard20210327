package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.BoardImgDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardImgMapperTest {
    @Autowired
    private BoardImgMapper boardImgMapper;
    @Test
    void findByBId() {
        List<BoardImgDto> imgs=boardImgMapper.findByBId(2);
        Assertions.assertNotNull(imgs);
    }
    @Test
    void insertOneAndDeleteOne() {
        BoardImgDto boardImgDto=new BoardImgDto();
        boardImgDto.setBId(2);
        boardImgDto.setImgPath("테스트용 이미지");
        int insert=boardImgMapper.insertOne(boardImgDto);
        int delete=boardImgMapper.deleteOne(boardImgDto.getBiId());
        Assertions.assertEquals(insert+delete,2);
    }
}
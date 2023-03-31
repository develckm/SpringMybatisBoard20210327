package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.BoardLikeDto;
import com.acorn.springboardteacher.dto.LikeStatusCntDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardLikeMapperTest {
    @Autowired
    private BoardLikeMapper boardLikeMapper;
    @Test
    void findByBIdAndUId(){
        BoardLikeDto boardLikeDto = boardLikeMapper.findByBIdAndUId( 1,"user01");
        assertNotNull(boardLikeDto);
    }
    @Test
    void countStatusByBId() {
        LikeStatusCntDto likeStatusCnt = boardLikeMapper.countStatusByBId(10);
        assertNotNull(likeStatusCnt);
    }

    @Test
    void countStatusByUId() {
        LikeStatusCntDto likeStatusCntDto = boardLikeMapper.countStatusByUId("user01");
        assertNotNull(likeStatusCntDto);
    }
    @Test
    void insertUpdateDeleteOne() {
        BoardLikeDto boardLike = new BoardLikeDto();
        boardLike.setBId(10);
        boardLike.setUId("user12");
        boardLike.setStatus("BEST");
        int insert = boardLikeMapper.insertOne(boardLike);
        boardLike.setStatus("BAD");
        int update = boardLikeMapper.updateOne(boardLike);
        int delete = boardLikeMapper.deleteOne(boardLike);
        assertEquals(insert+update+delete,3);
    }

}
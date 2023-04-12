package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.HashTagDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HashTagMapperTest {
    @Autowired
    private HashTagMapper hashTagMapper;
    @Test
    void findByTagContains() {
        List<HashTagDto> tags = hashTagMapper.findByTagContains("홍대");
        assertNotNull(tags);
    }

    @Test
    void findByTag() {
        HashTagDto tag = hashTagMapper.findByTag("홍대");
        assertNotNull(tag);
    }

    @Test
    void insertOne() {
        HashTagDto tag=hashTagMapper.findByTag("가록수길맛집");
        if(tag==null){
            int insert=hashTagMapper.insertOne("가록수길맛집");
        }
    }
}
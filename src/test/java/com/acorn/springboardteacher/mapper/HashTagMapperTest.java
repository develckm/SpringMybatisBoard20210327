package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.HashTagDto;
import groovy.util.logging.Log4j2;
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
    void findByName() {
        List<HashTagDto> tagList = hashTagMapper.findByNameContaining("홍");
        assertNotNull(tagList);
        System.out.println("tagList = " + tagList);
    }
}
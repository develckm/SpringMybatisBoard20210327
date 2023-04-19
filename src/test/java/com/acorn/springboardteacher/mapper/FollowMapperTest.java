package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.FollowDto;
import com.acorn.springboardteacher.dto.UserDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FollowMapperTest {
    @Autowired
    private FollowMapper followMapper;
    @Order(1)
    @Test
    void insertOne() {
        FollowDto followDto=new FollowDto();
        followDto.setFromId("user01");
        followDto.setToId("user17");
        int insert=followMapper.insertOne(followDto);
        assertEquals(insert,1);
    }
    @Order(2)
    @Test
    void findByFromId() {
        List<UserDto> followings=followMapper.findByFromId("user01");
        assertNotNull(followings);
    }
    @Order(3)
    @Test
    void findByToId() {
        List<UserDto> followers=followMapper.findByToId("user01");
        assertNotNull(followers);
    }
    @Order(4)
    @Test
    void deleteByFromIdAndToId() {
        FollowDto followDto=new FollowDto();
        followDto.setFromId("user01");
        followDto.setToId("user17");
        int delete=followMapper.deleteByFromIdAndToId(followDto);
        assertEquals(delete,1);
    }

}
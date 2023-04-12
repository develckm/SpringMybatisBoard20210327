package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.BoardImgDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardServiceImpTest {
    @Autowired
    private BoardService boardService;
    @Test
    void imgList() {
        List<BoardImgDto> boardImgDtos = boardService.imgList(new int[]{3, 4, 5});
        System.out.println("boardImgDtos = " + boardImgDtos);
        Assertions.assertNotNull(boardImgDtos);
    }
}
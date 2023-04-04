package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.BoardDto;

import java.util.List;

public interface BoardService {
    //리스트,상세(조회수올림),수정,등록,삭제
    List<BoardDto> list();
    BoardDto detail(int bId);
    int register(BoardDto board);
    int modify(BoardDto board);
    int remove(int bId);
}

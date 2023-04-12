package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.BoardDto;
import com.acorn.springboardteacher.dto.BoardImgDto;

import java.util.List;

public interface BoardService {
    //리스트,상세(조회수올림),수정,등록,삭제
    List<BoardDto> list();
    List<BoardDto> list(String loginUserId);

    List<BoardImgDto> imgList(int[]biId);

    BoardDto detail(int bId);
    int register(BoardDto board);
    int modify(BoardDto board, int[] delImgIds);
    int remove(int bId);
}

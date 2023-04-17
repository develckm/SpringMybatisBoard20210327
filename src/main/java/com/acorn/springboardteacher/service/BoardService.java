package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.BoardDto;
import com.acorn.springboardteacher.dto.BoardImgDto;
import com.acorn.springboardteacher.dto.PageDto;
import com.acorn.springboardteacher.dto.UserDto;

import java.util.List;

public interface BoardService {
    //리스트,상세(조회수올림),수정,등록,삭제
    List<BoardDto> list(UserDto loginUser, PageDto pageDto);
    List<BoardDto> tagList(String tag, UserDto loginUser);

    List<BoardImgDto> imgList(int[]biId);

    BoardDto detail(int bId);
    int register(BoardDto board, List<String> tags);
    int modify(BoardDto board, int[] delImgIds, List<String> tags, List<String> delTags);
    int remove(int bId);
}

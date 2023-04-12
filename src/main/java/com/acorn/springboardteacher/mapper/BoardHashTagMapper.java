package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.BoardHashTagDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardHashTagMapper {
    //게시글 + 태그리스트
    //태그 검색시 태그리스트 + 태그 수
    //태그 등록
    //태그 삭제
    List<BoardHashTagDto> findByBId(int bId);
    int countByTag(String tag);
    int insertOne(BoardHashTagDto boardHashTag);
    int deleteOne(BoardHashTagDto boardHashTag);

}

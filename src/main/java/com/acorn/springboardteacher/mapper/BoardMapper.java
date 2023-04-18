package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.BoardDto;
import com.acorn.springboardteacher.dto.BoardPageDto;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper //session Factory 에서 생성 및 관리
@Repository //spring Container 의 dao 의 의미
public interface BoardMapper {
    //리스트,상세,등록,수정,삭제,신고
    //상세보기시 조회수 올리기
    //com.github.pagehelper.Page > List+Page
    Page<BoardDto> findAll(BoardPageDto pageDto);
    List<BoardDto> findByTag(String tag);
    BoardDto findByBId(int bId);
    int insertOne(BoardDto board);
    int updateOne(BoardDto board);
    int deleteOne(int bId);
    int updateStatusByBId(BoardDto board);
    int updateIncrementViewCountBId(int bId);
}

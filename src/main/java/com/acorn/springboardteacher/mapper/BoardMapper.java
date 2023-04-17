package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.BoardDto;
import com.acorn.springboardteacher.dto.PageDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
//15분까지 쉬었다가 오세요
@Mapper //session Factory 에서 생성 및 관리
@Repository //spring Container 의 dao 의 의미
public interface BoardMapper {
    //리스트,상세,등록,수정,삭제,신고
    //상세보기시 조회수 올리기
    List<BoardDto> findAll(PageDto pageDto);
    List<BoardDto> findAll(String loginUserId);
    List<BoardDto> findByTag(String tag);
    BoardDto findByBId(int bId);
    int insertOne(BoardDto board);
    int updateOne(BoardDto board);
    int deleteOne(int bId);
    int updateStatusByBId(BoardDto board);
    int updateIncrementViewCountBId(int bId);
}

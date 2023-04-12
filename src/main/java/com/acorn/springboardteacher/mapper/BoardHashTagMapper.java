package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.BoardHashTagDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardHashTagMapper {
    List<BoardHashTagDto> findByBId(int bId);
    int insertOne(BoardHashTagDto boardHashTag);
    int countByHId(int hId);

}

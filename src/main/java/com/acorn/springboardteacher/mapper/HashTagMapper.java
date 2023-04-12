package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.BoardHashTagDto;
import com.acorn.springboardteacher.dto.HashTagDto;
import com.acorn.springboardteacher.dto.ReplyHashTagDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HashTagMapper {
    List<HashTagDto> findByNameContaining(String name);
    HashTagDto findByName(String name);
    String findNameByHId(int hId);
    int insertOne(HashTagDto hashTag);

}

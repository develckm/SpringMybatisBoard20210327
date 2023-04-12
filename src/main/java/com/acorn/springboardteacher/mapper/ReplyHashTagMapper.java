package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.ReplyHashTagDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyHashTagMapper {
    List<ReplyHashTagDto> findByBrId(int brId);
    int insertOne(ReplyHashTagDto boardHashTag);
    int countByHId(int hId);

}

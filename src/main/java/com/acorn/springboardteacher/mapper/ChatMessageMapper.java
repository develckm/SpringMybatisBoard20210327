package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.ChatMessageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMessageMapper {
    List<ChatMessageDto> findByCrIdOrderByPostTime(int crId);
    List<ChatMessageDto> findByCrIdAndPostGraterThenOrderByPostTime(@Param("crId") int crId, @Param("postTime") String postTime);
    int insertOne(ChatMessageDto chatMessageDto);
}

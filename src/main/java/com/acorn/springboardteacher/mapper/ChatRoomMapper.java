package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.ChatRoomDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ChatRoomMapper {
    int insertOne(ChatRoomDto chatRoom);
    List<ChatRoomDto> findAll();

}

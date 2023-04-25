package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.ChatMsgDto;
import com.acorn.springboardteacher.dto.ChatRoomDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMsgMapper {
    //구독(subscribe) 중인 룸에서 메세지 보낸다 (insert)
    //룸에서 메세지 리스트를 받아는다. (select)
    //가장 최근에 받은 메세지의 다음 메세지 리스트를 조회 (x)
    //삭제 x, 수정 x
    int insertOne(ChatMsgDto chatMsgDto);
    List<ChatMsgDto> findByCrId(int crId); //store
    List<ChatMsgDto> findByCrIdAndGraterThanPostTime(@Param("crId") int crId, @Param("postTime") String postTime);

}

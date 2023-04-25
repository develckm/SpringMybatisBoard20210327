package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.ChatRoomDto;
import com.acorn.springboardteacher.mapper.ChatRoomMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ChatRoomServiceImp implements  ChatRoomService{
    private ChatRoomMapper chatRoomMapper;
    @Override
    public List<ChatRoomDto> list() {
        List<ChatRoomDto> list=chatRoomMapper.findAll();
        return list;
    }

    @Override
    public int register(ChatRoomDto chatRoomDto) {
        return 0;
    }

    @Override
    public int modify(ChatRoomDto chatRoomDto) {
        return 0;
    }

    @Override
    public int remove(int crId) {
        return 0;
    }
}

package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.ChatMsgDto;

import java.util.List;

public interface ChatMsgService {
    //룸의 메세지 전체 조회
    //룸의 메세지 중에 마지막 조회한 내역 다음의 리스트 조회
    //룸에서 메세지 보내기
    int register(ChatMsgDto chatMsgDto);
    List<ChatMsgDto> list(int crId);
    List<ChatMsgDto> list(int crId,String postTime);

}

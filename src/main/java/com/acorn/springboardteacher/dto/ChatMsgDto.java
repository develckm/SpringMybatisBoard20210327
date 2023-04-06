package com.acorn.springboardteacher.dto;

import lombok.Data;

@Data
public class ChatMsgDto {
    enum MsgType{
        TALK,ENTER,LEAVE
    }
    private int roomId;
    private String uId;
    private String nickName;
    private String content;
    private MsgType msgType;
    private long sendTime;
}

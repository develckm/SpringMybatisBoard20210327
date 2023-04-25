package com.acorn.springboardteacher.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ChatMessageDto {
    public enum Status{
        ENTER,LEAVE,CHAT
    }
    private int cmId;
    private int crId;
    @JsonProperty("uId")
    private String uId;
    private String content;
    private Status status;
    private String nickname;
    @JsonFormat(timezone = "Asia/Seoul",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postTime;
}

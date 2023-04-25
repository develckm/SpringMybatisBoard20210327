package com.acorn.springboardteacher.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
@Data
public class ChatMsgDto {
    public enum Status{
        ENTER,LEAVE,CHAT
    }
    @JsonProperty("cmId")
    private int cmId;//pk
    @JsonProperty("crId")
    private  int crId;//fk chat_rooms cr_id
    @JsonProperty("uId")
    private String uId;// fk users u_id;
    private String nickname; //별칭
    private String content;//메세지 내용
    private Status status; //메세지 상태
    //jackson json 응답할때 Date 를 미국 시간대로 설정하더라
    @JsonFormat(timezone = "Asia/Seoul",pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("postTime")
    private Date postTime; //등록 시간
}

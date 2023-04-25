package com.acorn.springboardteacher.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChatMessageDto {
    private int cmId;
    private int crId;
    private String uId;
    private String content;
    private Date postTime;
}

package com.acorn.springboardteacher.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ChatRoomDto {
    private int crId;
    private String uId;
    private String name;
    private String description;
    private Date postTime;
    private Date updatedTime;
}

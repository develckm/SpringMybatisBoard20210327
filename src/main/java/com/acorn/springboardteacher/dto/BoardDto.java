package com.acorn.springboardteacher.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDto {
    private int bId;//pk
    private String uId;//fk user.u_id
    private Date postTime; //default CURRENT_TIMESTAMP
    private Date updateTime; //default on update CURRENT_TIMESTAMP
    private String status; //enum [PUBLIC,PRIVATE,REPORT,BLOCK]
    private String title;
    private String content;
    private int viewCount;
}

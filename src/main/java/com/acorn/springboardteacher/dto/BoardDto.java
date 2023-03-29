package com.acorn.springboardteacher.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BoardDto {
    private int bId;//pk
    private String uId;//fk user.u_id
    private Date postTime; //default CURRENT_TIMESTAMP
    private Date updateTime; //default on update CURRENT_TIMESTAMP
    private String status; //enum [PUBLIC,PRIVATE,REPORT,BLOCK]
    private String title="타이틀없음";
    private String content;
    private int viewCount;

    private UserDto user; //N : 1 = boards : users
    private List<BoardReplyDto> replies;//1 : N = boards : board_replies
    private List<BoardImgDto> imgs;//1:N = boars : board_imgs
}

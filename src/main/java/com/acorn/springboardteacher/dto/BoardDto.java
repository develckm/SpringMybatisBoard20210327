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
    private String loginUserLikeStatus; //로그인한 유저가 좋아요 한 내역 (board_likes uk(b_id,u_id))
    private LikeStatusCntDto likes; //1 : N = boards : board_likes 이지만 집계한 결과만 조회
    private UserDto user; //N : 1 = boards : users
    private List<BoardReplyDto> replies;//1 : N = boards : board_replies
    private List<BoardImgDto> imgs;//1:N = boards : board_imgs
    private List<BoardHashTagDto> tags; //1:N= boards : board_hashtags

}

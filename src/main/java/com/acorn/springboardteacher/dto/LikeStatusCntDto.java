package com.acorn.springboardteacher.dto;

import lombok.Data;
//board_likes and reply_likes
@Data
public class LikeStatusCntDto {
    //Status 의 집계(count) 결과
    private int like;
    private int sad;
    private int bad;
    private int best;
    private int id; //b_id or br_id
    private String status; //로그인한 사람이 누른 좋아요 내역
}

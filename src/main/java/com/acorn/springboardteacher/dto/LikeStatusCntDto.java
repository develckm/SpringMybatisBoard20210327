package com.acorn.springboardteacher.dto;

import lombok.Data;

@Data
public class LikeStatusCntDto {
    //Status 의 집계(count) 결과
    private int like;
    private int sad;
    private int bad;
    private int best;
    private String status; //로그인한 사람이 누른 좋아요 내역
}

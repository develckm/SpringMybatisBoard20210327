package com.acorn.springboardteacher.dto;

import lombok.Data;

@Data
public class LikeStatusCntDto {
    //Status 의 집계(count) 결과
    private int like;
    private int sad;
    private int bad;
    private int best;
}

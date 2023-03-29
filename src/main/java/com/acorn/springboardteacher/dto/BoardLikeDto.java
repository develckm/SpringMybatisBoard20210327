package com.acorn.springboardteacher.dto;

import lombok.Data;

//table board_likes
@Data
public class BoardLikeDto {
    //uk(b_id,u_id) : 유저가 게시글에 좋아요를 한번만 하게 하기 위해
    private int blId;//pk (Generate Key)
    private  int bId;//fk board.b_id
    private String uId;//fk user.u_id
    private String status;//['LIKE','BAD','SAD','BEST']
}

package com.acorn.springboardteacher.dto;

import lombok.Data;

//table reply_likes
@Data
public class ReplyLikeDto {
    //uk(br_id,u_id) : 유저가 댓글에 좋아요를 한번만 하게 하기 위해
    private int rlId;//pk (Generate Key)
    private  int brId;//fk board_reply.br_id
    private String uId;//fk user.u_id
    private String status;//['LIKE','BAD','SAD','BEST']
}

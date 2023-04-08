package com.acorn.springboardteacher.dto;

import lombok.Data;

import java.util.Date;

@Data
//uk(fromId,toId) 팔로워가 팔로잉은 한명만
public class FollowDto {
    private int fId; //fk
    private String fromId;//팔로워
    private String toId;//팔로잉
    private Date followTime;
}

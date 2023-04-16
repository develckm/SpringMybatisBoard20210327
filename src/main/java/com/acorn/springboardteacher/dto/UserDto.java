package com.acorn.springboardteacher.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String uId;
    private String pw;
    private String name;
    private String phone;
    private String imgPath;
    private String email;
    private java.util.Date postTime;
    private String birth;
    private String gender;
    private String address;
    private String detailAddress;
    private String permission;
    private boolean f4f;//followFormFollow 맞팔상태 or 로그인한 유저가 팔로우 한 사람인지
    private List<UserDto> followers; //나를 구독하는 사람들
    private List<UserDto> followings; //내가 구독하는 사람들
    //private List<UserDto> f4fs; //맞팔리스트


}

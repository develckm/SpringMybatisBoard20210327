package com.acorn.springboardteacher.dto;

import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    public enum StatusType{
        SIGNUP,EMAIL_CHECK,BLOCK,LEAVE,REPORT,NAVER,KAKAO,GOOGLE
    }
    public enum Gender{
        FEMALE,MALE,NONE
    }
    private String uId;
    private String pw;
    private String name;
    private String phone;
    private String imgPath;
    private String email;
    private java.util.Date postTime;
    private String birth;
    private Gender gender;
    private String address;
    private String detailAddress;
    private String permission;
    private StatusType status;
    private String emailCheckCode;


    //pojo (get set) 약속에서 boolean 타입은 get 이 아니라 is로 사용
    private boolean following; //로그인한 유저가 해당 유저를 팔로잉 중인가?
    private List<UserDto> followings; //팔로우 리스트 users : follows = 1 : N (from_id=u_id)
    private List<UserDto> followers; //팔로워 리스트 users : follows = 1 : N (to_id=u_id)

}

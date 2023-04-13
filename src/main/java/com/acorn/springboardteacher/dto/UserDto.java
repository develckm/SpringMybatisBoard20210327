package com.acorn.springboardteacher.dto;

import lombok.Data;

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
    private boolean f4f;//followFormFollow 맞팔
}

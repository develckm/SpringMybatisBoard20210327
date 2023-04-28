package com.acorn.springboardteacher.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class NaverUserDto {
    public enum Gender{
        F,M,N
    }
    private String id;
    private String name;
    private String mobile;
    @JsonSetter("mobile_e164")//naver 국제전화번호
    private String mobileE164;
    @JsonSetter("profile_image") //naver
    private String profileImage;
    private String email;
    private String birthday; //naver
    private String birthyear; //naver
    private Gender gender;
}

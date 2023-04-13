package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    //로그인,개인정보조회,수정,삭제(아이디 비밀번호 확인),회원가입,아이디찾기(email,phone,이름 입력시 아이디),
    //비밀번호찾기=>변경 ( [email or 핸드폰 인증] 로 변경페이지 반화(pwUpdate.do)=>변경Action)

    UserDto findByUId(String uId);
    UserDto findByUIdAndPw(UserDto user);
    String findUIdByEmailAndPhoneAndName(UserDto user); //아이디 찾기
    int updateOne(UserDto user);
    int updatePwByUId(UserDto user);
    int insertOne(UserDto user);
    int deleteByUIdAndPw(UserDto user);
    int setLoginUserId(String uId); //mysql에서 사용할 변수 등록
    int setLoginUserIdNull();
    List<UserDto> findByFollowFromId(String fromId); //users+follows 내가 팔로우 하는 유저
    List<UserDto> findByFollowToId(String fromId); //users+follows 나를 팔로우 하는 유저
    List<UserDto> findByFollowing_ToIdEqualsFollower_FromIdAndFollowing_FromId(String fromId); //users+following+follower 맞팔
}

package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //로그인,개인정보조회,수정,삭제(아이디 비밀번호 확인),회원가입,아이디찾기(email,phone,이름 입력시 아이디),
    //비밀번호찾기=>변경 ( [email or 핸드폰 인증] 로 변경페이지 반화(pwUpdate.do)=>변경Action)
    //이메일체크코드로 유저를 회원가입 상태로 변경
    int updateStatusByUidAndEmailCheckCode(UserDto user);

    UserDto findByUId(String uId);
    UserDto findByUIdAndPw(UserDto user);
    String findUIdByEmailAndPhoneAndName(UserDto user); //아이디 찾기
    int updateOne(UserDto user);
    int updatePwByUId(UserDto user);
    int insertOne(UserDto user);
    int deleteByUIdAndPw(UserDto user);
    int setLoginUserId(String uId); //mysql에서 사용할 변수 등록
    int setLoginUserIdNull();
}

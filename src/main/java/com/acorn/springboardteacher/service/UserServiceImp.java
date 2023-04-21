package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.UserDto;
import com.acorn.springboardteacher.mapper.UserMapper;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImp implements UserService{ //13분까지 쉬었다가 오세요~
    //@Component 로 정의된 클래스만 DI 할 수 있다.
    private UserMapper userMapper;
    //DIP 에 의해서 인터페이스로 정의
    //**인터페이스로 모듈을 유연하게 작성했기 때문에 Mybatis 가 객체로 구현할 수 있다.(DIP 덕분에 Mybatis 사용)
    public UserServiceImp(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    //POJO 로 DI 정의
    @Override
    public UserDto login(UserDto user) {
        return userMapper.findByUIdAndPw(user);
    }

    @Override
    public UserDto detail(String uId,String loginUserId) {
        userMapper.setLoginUserId(loginUserId);
        UserDto detail=userMapper.findByUId(uId);
        userMapper.setLoginUserIdNull();
        return detail;
    }

    @Override
    public int modify(UserDto user) {

        return userMapper.updateOne(user);
    }

    @Override
    public int modifyEmailCheck(UserDto user) {
        int modifyEmailCheck=userMapper.updateStatusByUidAndEmailCheckCode(user);
        return modifyEmailCheck;
    }

    @Override
    public int signup(UserDto user) {
        return userMapper.insertOne(user);
    }
    @Override
    public int dropout(UserDto user) {
        return userMapper.deleteByUIdAndPw(user);
    }
}

package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.UserDto;
import org.springframework.stereotype.Service;

//@Component //spring container(Inversion of Controller 컨테이너) 관리하는 bean 객체
//IoC(제어의 역전) : 객체를 내부에서 생성하는 것이 정상적인 제어고 컨테이너에서 객체를 주입하는 형태를 변경하는 것이 제어의 역전
//IoC 를 이용해서 관심사를 분리할 수 있고 이를 통해 관점지향언어(AOP)를 구현
//DI(의존성 주입) : pirvate 필드인 @Autowired or (생성자 or setter 함수로) 주입(POJO)
//DIP(의존성 주입 원칙) : 주입받는 객체의 타입은 꼭 인터페이스로 정의하라(모듈을 유연하게 확장하기 위해)
@Service //@Component 자식 어노테이션으로 service 관리 한는다는(관심사분리) 명시적의미와 @Transactional 을 정의 가능
public interface UserService {
    //로그인,유저상세,수정회원가입,삭제,이메일 체크시 가입확인
    UserDto login(UserDto user);
    UserDto detail(String uId,String loginUserId);
    int modify(UserDto user);
    int modifyEmailCheck(UserDto user);
    int signup(UserDto user);
    int dropout(UserDto user);

}

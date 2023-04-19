package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.UserDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//MethodOrderer.OrderAnnotation : @Order(0~) 순서를 정하겠다 (함수이름 등으로도 순서를 정할 수 있음)
class UserMapperTest {

    @BeforeAll //모든 테스트 실행전에 실행(초기화)
    static void init(){}
    @AfterAll //모든 테스트가 끝나고 실행(정리)
    static  void destroy(){}
    //@Test : 정의된 함수들은 순서 없이 실행기 때문에 초기화 하거나 정리를 해야한다.
    //등록 => 수정 => 삭제 (기대)
    //삭제(실패) =>등록(중) => 수정(실패)

    //***@TestMethodOrder : test의 순서를 지정할 수 있음

    @Autowired
    private UserMapper userMapper;

    private static UserDto user;
    @Test
    @Order(1)
    void insertOne() {
        user=new UserDto();
        user.setUId("TestTest478977");
        user.setPw("1234");
        user.setBirth("1986-05-25");
        user.setPhone("test-7777-test");
        user.setEmail("TestTest478977@Test.or.com.net");
        user.setName("테스트 유저 입니다.");
        user.setAddress("서울시 압구정");
        user.setDetailAddress("압구정역");
        user.setGender("MALE");
        user.setImgPath("/public/imgs/user/TestTest478977.jpeg");
        int insertOne = userMapper.insertOne(user);
        assertEquals(insertOne,1);
    }
    @Test
    @Order(2)
    void findByUId() {
        UserDto findUser=userMapper.findByUId("user01");
        System.out.println("findUser = " + findUser);
        assertNotNull(findUser);
    }
    @Test
    @Order(3)
    void findByUIdAndPw() {
        UserDto findUser=userMapper.findByUIdAndPw(user);
        assertNotNull(findUser);
    }
    @Test
    @Order(4)
    void findUIdByEmailAndPhoneAndName() {
        String uId=userMapper.findUIdByEmailAndPhoneAndName(user);
        assertNotNull(uId);
    }
    @Test
    @Order(5)
    void updateOne() {
        UserDto user=new UserDto();
        user.setUId("TestTest478977");
        user.setPw("12345678");
        user.setBirth("1986-05-20");
        user.setPhone("test-8888-test");
        user.setEmail("TestTest478977@Test.or.com");
        user.setName("테스트 유저 수정 입니다.");
        user.setAddress("서울시 강남구");
        user.setDetailAddress("강남역");
        user.setGender("NONE");
        user.setImgPath("/public/imgs/user/TestTest478977.png");
        int updateOne = userMapper.updateOne(user);
        UserMapperTest.user=userMapper.findByUId(user.getUId());
        System.out.println("UserMapperTest.user = " + UserMapperTest.user);
        assertEquals(updateOne,1);
    }
    @Test
    @Order(6)
    void updatePwByUId() {
        UserDto user=new UserDto();
        user.setUId("TestTest478977");
        user.setPw("비밀번호입니다.^^");
        int updatePwByUId = userMapper.updatePwByUId(user);
        UserMapperTest.user=userMapper.findByUId(user.getUId());
        System.out.println("UserMapperTest.user = " + UserMapperTest.user);
        assertEquals(1,updatePwByUId);
    }
    @Test
    @Order(7)
    void deleteByUIdAndPw() {
        int deleteByUIdAndPw = userMapper.deleteByUIdAndPw(user);
        assertEquals(1,deleteByUIdAndPw);
    }
}
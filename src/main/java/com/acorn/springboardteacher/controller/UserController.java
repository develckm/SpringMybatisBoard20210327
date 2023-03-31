package com.acorn.springboardteacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //< @Component 요청과 응답을 처리 가능
@RequestMapping("/user")
public class UserController {
    // "/user/login.do" 동적페이지 정의
    @GetMapping("/login.do")
    public String loginForm(){

        return "/user/loginForm"; //렌더할 뷰
    }
}

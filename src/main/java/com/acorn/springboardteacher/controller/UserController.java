package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.UserDto;
import com.acorn.springboardteacher.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor //모든 필드를 pojo 형식의 생성자로 자동 생성
@Controller //< @Component 요청과 응답을 처리 가능
@RequestMapping("/user")
@Log4j2 //log 필드로 로그남길 수 있다.(파일로 저장 가능[유지기간,성질])
//**System.out.print() => 휘발성 로그 (콘솔에 출력만 가능!)
public class UserController {
    // "/user/login.do" 동적페이지 정의

    private UserService userService;

    @GetMapping("/login.do")
    public String loginForm(){

        return "/user/loginForm"; //렌더할 뷰
    }
    @PostMapping("/login.do")
    public String loginAction(
            UserDto user,
            Integer autoLogin,
            HttpSession session,
            RedirectAttributes redirectAttributes){
        //redirect 페이지에 메세지를 전달하는 방법 ~2가지
        //1. 파라미터로 ?msg=로그인 성공 (권장 x)
        //2. Session 에서 추가한 후에 사용하고 삭제 (권장 o)
        //3. redirectAttributes.addFlashAttribute("msg","로그인성공"); 세션저장되었다가 사용하면 바로 삭제
        UserDto loginUser=null;
        try {
            loginUser=userService.login(user);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        if(loginUser!=null){
            redirectAttributes.addFlashAttribute("msg","로그인 성공");
            session.setAttribute("loginUser",loginUser);
            return "redirect:/";
            //*get 을 제외한 다른 메스드는 양식을 제출하거나 ajax로 페이지를 호출할때만 가능
        }else{
            redirectAttributes.addFlashAttribute("msg","아이디나 패스워드를 확인하세요!");
            return "redirect:/user/login.do";
        }
    }
}

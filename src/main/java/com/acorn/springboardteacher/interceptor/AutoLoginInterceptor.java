package com.acorn.springboardteacher.interceptor;

import com.acorn.springboardteacher.dto.UserDto;
import com.acorn.springboardteacher.lib.AESEncryption;
import com.acorn.springboardteacher.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
@AllArgsConstructor
@Log4j2
public class AutoLoginInterceptor implements HandlerInterceptor {
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        Object loginUserObj=session.getAttribute("loginUser");
        if(loginUserObj!=null) return true;

        Cookie[] cookies =request.getCookies();
        if (cookies==null) return true;
        Cookie loginId=null;
        Cookie loginPw=null;
        for (Cookie c : cookies){
            if(c.getName().equals("SPRING_LOGIN_ID")){
                loginId=c;
            }else if(c.getName().equals("SPRING_LOGIN_PW")){
                loginPw=c;
            }
        }
        if(loginId!=null && loginPw!=null){
            UserDto loginUser=null;
            String msg=null;
            try {
                UserDto user=new UserDto();
                //FqBqSA2/0xW9LbYtu2h2Jw== (해시코드)  =>(복호화) "user01" (평문)
                //9YnqkRYf2AT+a7hmuVBK7g==   => "1234"
                user.setUId(AESEncryption.decryptValue(loginId.getValue()));
                user.setPw(AESEncryption.decryptValue(loginPw.getValue()));
                loginUser=userService.login(user);
            }catch (Exception e){
                log.error(e.getMessage());
            }
            if(loginUser!=null){
                msg="자동 로그인 성공";
                session.setAttribute("loginUser",loginUser); //로그인 성공
                session.setAttribute("msg",msg);
                return true;
            }else{
                msg="자동 로그인 실패 (쿠키 정보를 삭제합니다. 다시 로그인하세요.)";
                loginId.setMaxAge(0);
                loginPw.setMaxAge(0);
                loginId.setPath("/");
                loginPw.setPath("/");
                response.addCookie(loginId);
                response.addCookie(loginPw);
                session.setAttribute("msg",msg);
                response.sendRedirect("/user/login.do");
                return false;
            }
        }
        return true;
    }
}

package com.acorn.springboardteacher.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Component
@Log4j2
public class LoginCheckInterceptor implements HandlerInterceptor {
    //필터 보다 기능이 훨얼씬 많이 추가된 미들웨어

    //필터가 가능한 곳 (컨트롤러,서블릿(동적페이지) 요청 전)
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //return true; //원래 요청하던 페이지로 이동
        HttpSession session=request.getSession();
        Object loginUserObj=session.getAttribute("loginUser");
        String uri=request.getRequestURI();
        if(loginUserObj!=null){
            return true;
        }else{
            session.setAttribute("redirectPage",uri);//로그인 성공시 원래 가려던 페이지로 redirect 예정
            session.setAttribute("msg","로그인 시 이용가능 한 페이지 입니다.");
            response.sendRedirect("/user/login.do");
            return false;
        }
    }
    //요청이 처리가 끝나고 뷰를 렌더하기 직전
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    //view 렌더가 끝나면 (html 을 추가하고 싶을때)
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}

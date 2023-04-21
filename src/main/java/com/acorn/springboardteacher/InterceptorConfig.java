package com.acorn.springboardteacher;

import com.acorn.springboardteacher.interceptor.AutoLoginInterceptor;
import com.acorn.springboardteacher.interceptor.LoginCheckInterceptor;
import com.acorn.springboardteacher.interceptor.MsgRemoveInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration// > @Component  스프링 실행시 설정 파일
//WebMvcConfigurer : 요청과 응답과 관련된 설정
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private AutoLoginInterceptor autoLoginInterceptor;
    private LoginCheckInterceptor loginCheckInterceptor;
    private MsgRemoveInterceptor msgRemoveInterceptor;
    @Override
    //addInterceptors : 인터셉터 설정
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoLoginInterceptor).order(1)
                .addPathPatterns("/**");
        registry.addInterceptor(loginCheckInterceptor).order(2)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login.do")
                .excludePathPatterns("/user/signup.do")
                .excludePathPatterns("/user/emailCheck.do")
                .addPathPatterns("/board/**")
                .excludePathPatterns("/board/list.do")
                .excludePathPatterns("/board/*/tagList.do")
                .excludePathPatterns("/board/*/ajaxTagList.do")
                .excludePathPatterns("/board/*/detail.do");
        registry.addInterceptor(msgRemoveInterceptor).order(3)
                .addPathPatterns("/**");
    }
}

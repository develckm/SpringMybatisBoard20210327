package com.acorn.springboardteacher;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration//springboot 설정 파일
@EnableWebSocket//소켓으로 요청이 왔을 때 처리하는 설정
public class ChatSocketConfig implements WebSocketConfigurer {
    private ChatSocketHandler chatSocketHandler;

    public ChatSocketConfig(ChatSocketHandler chatSocketHandler) {
        this.chatSocketHandler = chatSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //webSocket 통신은 ws://localhost:8080 로 접속하고
        //http 통신은 http://localhost:8080 로 접속하기 때문에 다른 서버에서 접속한다고 생각해서 CORS 에 위반
        registry.addHandler(chatSocketHandler,"chat").setAllowedOrigins("*"); //CORS 문제 해결
        //ws://localhost:8080/chat 으로 접속하면 소켓 연결
    }
}

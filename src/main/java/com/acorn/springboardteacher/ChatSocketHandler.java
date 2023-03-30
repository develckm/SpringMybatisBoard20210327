package com.acorn.springboardteacher;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class ChatSocketHandler extends TextWebSocketHandler {
    //채팅방에 접속하면 유지되는 소켓 리스트
    private static List<WebSocketSession> sessionList=new ArrayList<>();
    //채팅방에 유저가 접속하면 실행
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);
        log.info(session+" 클라이언트님 접속");
    }

    //채팅방에 유저가 소켓을 통해 메세지를 보낼때 실행
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload=message.getPayload();
        log.info("client 가 보낸 메세지 정보: "+payload);
        //접속한 모든 사람에게 메세지 전달
        for(WebSocketSession client : sessionList){
            client.sendMessage(message);
        }

    }
    //채팅방에서 접속을 해제하면 실행
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        log.info(session+" 클라이언트님 퇴장");
    }
}

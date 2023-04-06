package com.acorn.springboardteacher;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class STOMPChatConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //웹소켓이 없는 클라이 언트와 통신할 때 (websocket, xhr-streaming, xhr-polling 등을 사용해서 통신 시도하는 경로)
        registry.addEndpoint("/chatAppSockJs").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        //메세지를 서버에 보내는 url(prefix) 지정 예) /app/chat :로 메세지를 보내면 메세지 브로커가 enableSimpleBroker 로 정의한 url 을
        registry.enableSimpleBroker("/topic");
        //서버에서 받은 메세지를 구독하는(subscribe)곳에 보내주는 데 이때 구독 url 지정
        //@Controller 에서 @MessageMapping("/hello") /app/hello에서 서버로 보낸 메세지를    @SendTo("/topic/greetings") 를 구독하는 클라이언트로 메세지 전달
    }
}

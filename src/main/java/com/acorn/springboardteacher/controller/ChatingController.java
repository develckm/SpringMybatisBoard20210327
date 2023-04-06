package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.ChatMsgDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class ChatingController {
    private SimpMessageSendingOperations sendingOperations;
    //구독하는 url 상세하게 만드는 객체
    @MessageMapping("/send")
    //@SendTo("/topic/{roomNo}/receive")
    public void chatRoomBroker(ChatMsgDto chatMsgDto){
        sendingOperations.convertAndSend("/topic/"+chatMsgDto.getRoomId()+"/receive",chatMsgDto);
    }
}

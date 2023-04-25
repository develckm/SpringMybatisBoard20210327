package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.ChatRoomDto;
import com.acorn.springboardteacher.mapper.ChatMessageMapper;
import com.acorn.springboardteacher.mapper.ChatRoomMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {
    private ChatRoomMapper chatRoomMapper;
    private ChatMessageMapper chatMessageMapper;

    @GetMapping("/room/list.do")
    public String list(Model model){

        List<ChatRoomDto> all = chatRoomMapper.findAll();
        model.addAttribute("rooms",all);
        return "/chat/list";
    }

    @GetMapping("/room/{roomId}/detail.do")
    public String detail(Model model){

        List<ChatRoomDto> all = chatRoomMapper.findAll();
        model.addAttribute("rooms",all);
        return "/chat/detail";
    }

}

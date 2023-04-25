package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.ChatMessageDto;
import com.acorn.springboardteacher.dto.ChatRoomDto;
import com.acorn.springboardteacher.mapper.ChatMessageMapper;
import com.acorn.springboardteacher.mapper.ChatRoomMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String detail(
            Model model,
            @PathVariable int roomId){

        List<ChatRoomDto> all = chatRoomMapper.findAll();
        model.addAttribute("rooms",all);
        model.addAttribute("roomId",roomId);
        return "/chat/detail";
    }
    @GetMapping("/msg/{roomId}/list.do")
    public @ResponseBody List<ChatMessageDto> msgs(
            Model model,
            @PathVariable int roomId,
            @RequestParam(required = false)String postTime){
        List<ChatMessageDto> all=null;
        if(postTime!=null){
            all = chatMessageMapper.findByCrIdAndPostGraterThenOrderByPostTime(roomId,postTime);
        }else{
            all = chatMessageMapper.findByCrIdOrderByPostTime(roomId);
        }
        return all;
    }
    @PostMapping("/msg/register.do")
    public @ResponseBody int register(@ModelAttribute ChatMessageDto chatMessageDto){
        return chatMessageMapper.insertOne(chatMessageDto);
    }
}

package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.UserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ChatController {
    @GetMapping("/chattingRoom.do")
    public String chattingRoom(
            @SessionAttribute(required = false) UserDto loginUser,
            RedirectAttributes redirectAttributes){
        if(loginUser==null){
            redirectAttributes.addFlashAttribute("msg","로그인 한 유저만 채팅방 입장 가능");
            return "redirect:/user/login.do";
        }
        return "/chattingRoom";
    }
}

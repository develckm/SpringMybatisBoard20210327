package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.BoardReplyDto;
import com.acorn.springboardteacher.dto.UserDto;
import com.acorn.springboardteacher.mapper.BoardReplyMapper;
import com.acorn.springboardteacher.service.BoardReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reply")
@Log4j2
@AllArgsConstructor
public class ReplyController {
    private BoardReplyService boardReplyService;
    @PostMapping("/insert.do")
    public String insertAction(
            @ModelAttribute BoardReplyDto reply,
            RedirectAttributes redirectAttributes,
            @SessionAttribute UserDto loginUser){
        int register=0;
        String msg="댓글 등록 실패";
        try {
            register=boardReplyService.register(reply);
        }catch (Exception e){
            log.error(e.getMessage());
            msg+=" 에러 :"+e.getMessage();
        }
        if(register>0){
            msg="댓글 등록 성공";
        }
        redirectAttributes.addFlashAttribute("msg",msg);
        return "redirect:/board/"+reply.getBId()+"/detail.do";
    }
}

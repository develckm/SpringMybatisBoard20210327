package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.BoardDto;
import com.acorn.springboardteacher.dto.UserDto;
import com.acorn.springboardteacher.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/board")
@AllArgsConstructor
@Log4j2
public class BoardController {
    private BoardService boardService;

    @GetMapping("/list.do")
    public String list(
            Model model,
            @SessionAttribute(required = false) UserDto loginUser){

        List<BoardDto> boards;
        if(loginUser==null){
            boards=boardService.list();
        }else{
            boards=boardService.list(loginUser.getUId());
        }
        model.addAttribute("boards",boards);

        return "/board/list";
    }
    //?bId=1 //bId 동적 동적 페에지에 꼭 필요(400) 명시적으로 나타내는 것
    @GetMapping("/{bId}/detail.do")
    public String detail(
            Model model,
            @PathVariable int bId){
        BoardDto board=boardService.detail(bId);
        model.addAttribute("b",board);
        return "/board/detail";
    }
    @GetMapping("/register.do")
    public void registerForm(@SessionAttribute UserDto loginUser){}
    @PostMapping("/register.do")
    public String registerAction(
            @SessionAttribute UserDto loginUser,
            @ModelAttribute BoardDto board,
            MultipartFile [] imgs){
        String redirectPage="redirect:/board/register.do";
        if(!loginUser.getUId().equals(board.getUId())) return redirectPage;
        log.info(board);
        return redirectPage;
    }
}

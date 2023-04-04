package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.BoardDto;
import com.acorn.springboardteacher.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    @GetMapping("/list.do")
    public String list(Model model){
        List<BoardDto> boards=boardService.list();
        model.addAttribute("boards",boards);

        return "/board/list";
    }
}

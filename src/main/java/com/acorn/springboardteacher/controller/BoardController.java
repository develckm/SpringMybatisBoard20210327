package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.BoardDto;
import com.acorn.springboardteacher.dto.BoardImgDto;
import com.acorn.springboardteacher.dto.UserDto;
import com.acorn.springboardteacher.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
@Log4j2
public class BoardController {
    private BoardService boardService;
    @Value("${img.upload.path}")
    private String uploadPath; //등록 (프로젝트위치+/static/public/img)
    @Value("${static.path}")
    private String staticPath; //삭제 (imgPath 를 정적 리소스 경로로 하기 때문)
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

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
            @RequestParam(name = "img") MultipartFile [] imgs) throws IOException {

        String redirectPage="redirect:/board/register.do";
        if(!loginUser.getUId().equals(board.getUId())) return redirectPage;
        List<BoardImgDto> imgDtos=null;
        if(imgs!=null){
            imgDtos=new ArrayList<>();
            for(MultipartFile img : imgs){
                if(!img.isEmpty()){
                    String[] contentTypes=img.getContentType().split("/"); // text/xml application/json image/png
                    if(contentTypes[0].equals("image")){
                        String fileName=System.currentTimeMillis()+"_"+(int)(Math.random()*10000)+"."+contentTypes[1];
                        Path path= Paths.get(uploadPath+"/board/"+fileName);
                        img.transferTo(path);
                        BoardImgDto imgDto=new BoardImgDto();
                        imgDto.setImgPath("/public/img/board/"+fileName);
                        //10분까지 쉬었다가 오세요~
                    }
                }
            }
        }

        return redirectPage;
    }
}

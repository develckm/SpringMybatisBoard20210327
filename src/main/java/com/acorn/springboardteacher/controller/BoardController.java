package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.BoardDto;
import com.acorn.springboardteacher.dto.BoardImgDto;
import com.acorn.springboardteacher.dto.BoardPageDto;
import com.acorn.springboardteacher.dto.UserDto;
import com.acorn.springboardteacher.service.BoardService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
            @SessionAttribute(required = false) UserDto loginUser,
            @ModelAttribute BoardPageDto pageDto){

        List<BoardDto> boards;
        boards=boardService.list(loginUser,pageDto);
        PageInfo<BoardDto> pageBoards=new PageInfo<>(boards);
        model.addAttribute("page",pageBoards);
        model.addAttribute("boards",boards);
        return "/board/list";
    }
    @GetMapping("/{tag}/tagList.do")
    public String tagList(
            @PathVariable String tag,
            Model model,
            @SessionAttribute(required = false) UserDto loginUser,
            BoardPageDto pageDto){
        List<BoardDto> boards;
        pageDto.setPageSize(4);
        boards=boardService.tagList(tag,loginUser,pageDto);
        model.addAttribute("boards",boards);
        model.addAttribute("tag",tag);
        return "/board/tagList";
    }
    @GetMapping("/{tag}/ajaxTagList.do")
    public String ajaxTagList(
            @PathVariable String tag,
            Model model,
            @SessionAttribute(required = false) UserDto loginUser,
            BoardPageDto pageDto){
        List<BoardDto> boards;
        pageDto.setPageSize(4);
        boards=boardService.tagList(tag,loginUser,pageDto);
        model.addAttribute("boards",boards);
        model.addAttribute("tag",tag);
        return "/board/includeList";
    }

    //?bId=1 //bId 동적 동적 페에지에 꼭 필요(400) 명시적으로 나타내는 것
    @GetMapping("/{bId}/modify.do")
    public String modifyForm(
            Model model,
            @PathVariable int bId,
            @SessionAttribute UserDto loginUser){
        BoardDto board=boardService.detail(bId);
        model.addAttribute("b",board);
        return "/board/modify";
    }
    @PostMapping("/modify.do")
    public String modifyAction(
            @ModelAttribute BoardDto board,
            @RequestParam(value = "delImgId",required = false) int [] delImgIds,
            @RequestParam(value = "img",required = false) MultipartFile [] imgs,
            @RequestParam(value = "tag",required = false) List<String> tags,
            @RequestParam(value = "delTag",required = false) List<String> delTags
            ){
        String redirectPath="redirect:/board/"+board.getBId()+"/modify.do";
        List<BoardImgDto> imgDtos=null;
        int modify=0;
        try {
            if(delImgIds!=null)imgDtos=boardService.imgList(delImgIds);
            //삭제 전에 이미지 파일 경로를 받아옴
            modify=boardService.modify(board,delImgIds,tags,delTags);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        if(modify>0){
            if (imgDtos!=null){
                for (BoardImgDto i : imgDtos){
                    File imgFile=new File(staticPath+i.getImgPath());
                    if(imgFile.exists())imgFile.delete();
                }
            }
            redirectPath="redirect:/board/list.do";
        }
        return redirectPath;
    }
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
            @RequestParam(name = "img",required = false) MultipartFile [] imgs,
            @RequestParam(name = "tag",required = false) List<String> tags) throws IOException {

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
                        imgDtos.add(imgDto);
                    }
                }
            }
        }
        board.setImgs(imgDtos);
        int register=0;
        try {
            register=boardService.register(board,tags);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        if (register>0){
            redirectPage="redirect:/board/list.do";
        }else{
            //등록 실패시 저장했던 파일 삭제~
            if(imgDtos!=null){
                for(BoardImgDto i : imgDtos){
                    File imgFile=new File(staticPath+i.getImgPath());
                    if(imgFile.exists())imgFile.delete();
                }
            }
        }

        return redirectPage;
    }
    @GetMapping("/{bId}/remove.do")
    public String removeAction(
            @PathVariable int bId,
            @SessionAttribute UserDto loginUser,
            RedirectAttributes redirectAttributes){
        String redirectPath="redirect:/board/"+bId+"/modify.do";
        String msg="삭제 실패";
        BoardDto board=null;
        List<BoardImgDto> imgDtos=null;
        int remove=0;
        try {
            board=boardService.detail(bId);
            imgDtos=board.getImgs();
            remove=boardService.remove(bId);
        }catch (Exception e){
            log.error(e);
        }
        if(remove>0){//이미지 파일 삭제
            if(imgDtos!=null){
                for (BoardImgDto i: imgDtos){
                    File imgFile=new File(staticPath+i.getImgPath());
                    if(imgFile.exists())imgFile.delete();
                }
            }
            msg="삭제 성공!";
            redirectPath="redirect:/board/list.do";
        }
        redirectAttributes.addFlashAttribute("msg",msg);
        return redirectPath;
    }
}


















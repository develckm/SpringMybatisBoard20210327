package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.BoardLikeDto;
import com.acorn.springboardteacher.dto.LikeStatusCntDto;
import com.acorn.springboardteacher.dto.UserDto;
import com.acorn.springboardteacher.service.BoardLikeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board/like")
@AllArgsConstructor
@Log4j2
public class BoardLikeController {
    private BoardLikeService boardLikeService;
    @GetMapping("/{bId}/read.do")
    public String readLikeStatusCnt(
            @PathVariable int bId,
            @SessionAttribute(required = false)UserDto loginUser,
            Model model){
        String templatePage;
        LikeStatusCntDto likes;
        model.addAttribute("id",bId);
        if(loginUser!=null){
            likes=boardLikeService.read(bId,loginUser.getUId());
            templatePage="/board/loginLikes";
        }else{
            likes=boardLikeService.read(bId);
            templatePage="/board/likes";
        }
        log.info(likes);
        model.addAttribute("likes",likes);
        return templatePage;
    }
    @Data
    class HandlerDto{
        enum HandlerType{REGISTER,MODIFY,REMOVE}
        private HandlerType handlerType;
        private String status;
        int handler; //0실패 1성공
    }
    @GetMapping("/{status}/{bId}/handler.do")
    public @ResponseBody HandlerDto handler(
            @PathVariable String status,
            @PathVariable int bId,
            @SessionAttribute UserDto loginUser){
        HandlerDto handlerDto=new HandlerDto();
        handlerDto.setStatus(status);

        BoardLikeDto boardLike=boardLikeService.detail(bId,loginUser.getUId());

        int handler=0;
        BoardLikeDto like=new BoardLikeDto();
        like.setStatus(status);
        like.setUId(loginUser.getUId());
        like.setBId(bId);
        if(boardLike==null){ //등록
            handlerDto.setHandlerType(HandlerDto.HandlerType.REGISTER);
            handler=boardLikeService.register(like);
        }else{ //수정 or 삭제
            if(boardLike.getStatus().equals(status)) {//삭제 좋아요가 눌렀는데 다시 좋아요를 누른것
                handlerDto.setHandlerType(HandlerDto.HandlerType.REMOVE);
                handler=boardLikeService.remove(like);
            }else { //수정 좋아요를 눌렀는데 싫어요를 누른것
                handlerDto.setHandlerType(HandlerDto.HandlerType.MODIFY);
                handler=boardLikeService.modify(like);
            }
        }
        handlerDto.setHandler(handler);

        return handlerDto;
    }
}

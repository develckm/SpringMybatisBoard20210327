package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.BoardReplyDto;
import com.acorn.springboardteacher.dto.UserDto;
import com.acorn.springboardteacher.mapper.BoardReplyMapper;
import com.acorn.springboardteacher.service.BoardReplyService;
import lombok.AllArgsConstructor;
import lombok.Data;
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
import java.util.List;

@Controller
@RequestMapping("/reply")
@Log4j2
public class ReplyController {
    private BoardReplyService boardReplyService;
    @Value("${img.upload.path}") //application.yml의 설정 값 가져오기
    private String imgUploadPath;
    @Value("${static.path}")
    private String staticPath;
    public ReplyController(BoardReplyService boardReplyService) {
        this.boardReplyService = boardReplyService;
    }
    @GetMapping("/{brId}/detail.do")
    public @ResponseBody BoardReplyDto detail(@PathVariable int brId){
        BoardReplyDto reply=boardReplyService.detail(brId); //프록시 객체
        log.info(reply);
        return reply;
    }
    @GetMapping("/{bId}/list.do")
    public String list(
            @PathVariable int bId,
            Model model){
        List<BoardReplyDto> replies=boardReplyService.list(bId);
        model.addAttribute("replies",replies);
        return "/reply/list";
    }
    @Data
    class HandlerDto{
        private int register;
        private int modify;
        private int remove;
    }
    // @ResponseBody HandlerDto : view 를 응답하지 않고 해당 객체를 json 으로 파싱해서 패포!
    @PostMapping("/handler.do")
    public @ResponseBody HandlerDto registerHandler(
            @ModelAttribute BoardReplyDto reply,
            @SessionAttribute UserDto loginUser, //400
            MultipartFile img) throws IOException {
        HandlerDto handlerDto=new HandlerDto();
        if(!img.isEmpty()){
            String[] cotentTypes=img.getContentType().split("/");
            if(cotentTypes[0].equals("image")){
                String fileName=System.currentTimeMillis()+"_"+(int)(Math.random()*10000)+"."+cotentTypes[1];
                Path path=Paths.get(imgUploadPath+"/reply/"+fileName);
                img.transferTo(path);
                //fetch 에서 resp.status 200 일때만 처리하기 때문에 그냥 오류가 발생하면 500
                reply.setImgPath("/public/img/reply/"+fileName);
            }
        }
        int register=boardReplyService.register(reply); //500
        handlerDto.setRegister(register);
        return handlerDto;
    }
    @PutMapping("/handler.do")
    public @ResponseBody HandlerDto modify(
            @ModelAttribute BoardReplyDto reply,
            MultipartFile img,
            @SessionAttribute UserDto loginUser) throws IOException {
        HandlerDto handlerDto=new HandlerDto();
        if(!img.isEmpty()){
            String [] contentTypes=img.getContentType().split("/");
            if(contentTypes[0].equals("image")){
                String fileName=System.currentTimeMillis()+"_"+(int)(Math.random()*10000)+"."+contentTypes[1];
                Path path=Paths.get(imgUploadPath+"/reply/"+fileName);
                img.transferTo(path);//저장
                //만약에 수정하기 전 이미지파일이 있으면 삭제
                if(reply.getImgPath()!=null){
                    File imgFile = new File(staticPath + reply.getImgPath());
                    if(imgFile.exists())imgFile.delete();
                }
                //새로 등록된 파일을 set
                reply.setImgPath("/public/img/reply/"+fileName);
            }
        }
        int modify=boardReplyService.modify(reply);
        handlerDto.setModify(modify);
        return handlerDto;
    }

    @DeleteMapping("/handler.do")
    public @ResponseBody HandlerDto remove(
            BoardReplyDto reply,
            @SessionAttribute UserDto loginUser){
        HandlerDto handlerDto=new HandlerDto();
        int remove= boardReplyService.remove(reply.getBrId());
        handlerDto.setRemove(remove);
        if(remove>0 && reply.getImgPath()!=null){
            File imgFile=new File(staticPath+reply.getImgPath());
            if(imgFile.exists())imgFile.delete();
        }
        return handlerDto;
    }



    @PostMapping("/insert.do")
    public String insertAction(
            @ModelAttribute BoardReplyDto reply,
            RedirectAttributes redirectAttributes,
            @SessionAttribute UserDto loginUser,
            MultipartFile img){//임시저장소에 잠시 대기하다가 아무것도 하지 않으면 삭제
        int register=0;
        String msg="댓글 등록 실패";
        log.info(imgUploadPath);
        try {
            if(img!=null && !img.isEmpty()){//파일을 선택하지 않아도 null 이 아님
                String contentType=img.getContentType(); // image/png or image/jpeg or text/xml or application/json
                String [] contentTypes=contentType.split("/");
                if(contentTypes[0].equals("image")){
                    String fileName=System.currentTimeMillis()+"_"+(int)(Math.random()*10000)+"."+contentTypes[1];
                    String imgPath=imgUploadPath+"/reply/"+fileName; //서버 컴퓨터의 물리적 위치
                    Path path= Paths.get(imgPath);
                    img.transferTo(path); //이미지 저장
                    reply.setImgPath("/public/img/reply/"+fileName); //서버가 이미지를 배포하는 위치
                }
            }
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

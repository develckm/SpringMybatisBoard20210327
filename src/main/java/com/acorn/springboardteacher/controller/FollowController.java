package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.FollowDto;
import com.acorn.springboardteacher.dto.UserDto;
import com.acorn.springboardteacher.service.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
@AllArgsConstructor
public class FollowController {
    private FollowService followService;
    @PostMapping("/{uId}/{follower}/handler.do")
    public int registerHandler(
            @PathVariable String uId,
            @PathVariable boolean follower,
            @SessionAttribute UserDto loginUser){ //400
        int register=0;
        //글쓴이가 자기 자신을 팔로잉 할 수 없다.
        if(loginUser.getUId().equals(uId))return register;

        FollowDto followDto=new FollowDto();
        if(follower){ //팔로워 등록
            followDto.setToId(loginUser.getUId());
            followDto.setFromId(uId);
        }else{//팔로우 등록
            followDto.setToId(uId);
            followDto.setFromId(loginUser.getUId());
        }
        register=followService.register(followDto); //500
        return register;
    }
    @DeleteMapping("/{uId}/{follower}/handler.do")
    public int removeHandler(
            @PathVariable String uId,
            @PathVariable boolean follower,
            @SessionAttribute UserDto loginUser){
        int remove=0;
        FollowDto followDto=new FollowDto();
        if(follower){//팔로워 삭제
            followDto.setFromId(uId);
            followDto.setToId(loginUser.getUId());
        }else{//팔로잉 삭제
            followDto.setFromId(loginUser.getUId());
            followDto.setToId(uId);
        }
        remove= followService.remove(followDto);
        return remove;
    }
}

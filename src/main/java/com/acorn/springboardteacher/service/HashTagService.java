package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.BoardHashTagDto;
import com.acorn.springboardteacher.dto.HashTagDto;
import com.acorn.springboardteacher.dto.ReplyHashTagDto;
import com.acorn.springboardteacher.mapper.HashTagMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HashTagService {

    List<HashTagDto> searchName(String name);
    int  replyHashTagRegister(ReplyHashTagDto replyHashTag);
    int  boardHashTagRegister(BoardHashTagDto boardHashTag);

}

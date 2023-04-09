package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.BoardHashTagDto;
import com.acorn.springboardteacher.dto.HashTagDto;
import com.acorn.springboardteacher.dto.ReplyHashTagDto;
import com.acorn.springboardteacher.mapper.HashTagMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class HashTagServiceImp implements HashTagService{
    private HashTagMapper hashTagMapper;
    public List<HashTagDto> searchName(String name){
        List<HashTagDto> searchName=hashTagMapper.findByNameContaining(name);
        return searchName;
    }
    @Transactional
    public int  replyHashTagRegister(ReplyHashTagDto replyHashTag){
        HashTagDto hashTag=hashTagMapper.findByName(replyHashTag.getName());
        int register=0;
        if (hashTag==null){
            hashTag=new HashTagDto();
            hashTag.setName(replyHashTag.getName());
            register=hashTagMapper.insertOne(hashTag);
        }
        replyHashTag.setHId(hashTag.getHId());
        register=hashTagMapper.replyHashTagInsertOne(replyHashTag);
        return register;

    }
    @Transactional
    public int  boardHashTagRegister(BoardHashTagDto boardHashTag){
        HashTagDto hashTag=hashTagMapper.findByName(boardHashTag.getName());
        int register=0;
        if (hashTag==null){
            hashTag=new HashTagDto();
            hashTag.setName(boardHashTag.getName());
            register=hashTagMapper.insertOne(hashTag);
        }
        boardHashTag.setHId(hashTag.getHId());
        register=hashTagMapper.boardHashTagInsertOne(boardHashTag);
        return register;

    }
}

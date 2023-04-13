package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.BoardHashTagDto;
import com.acorn.springboardteacher.dto.HashTagDto;
import com.acorn.springboardteacher.dto.ReplyHashTagDto;
import com.acorn.springboardteacher.mapper.BoardHashTagMapper;
import com.acorn.springboardteacher.mapper.HashTagMapper;
import com.acorn.springboardteacher.mapper.ReplyHashTagMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HashTagServiceImp implements HashTagService{
    private HashTagMapper hashTagMapper;
    private BoardHashTagMapper boardHashTagMapper;
    private ReplyHashTagMapper replyHashTagMapper;
    @Override
    public int  register(ReplyHashTagDto replyHashTag){
        HashTagDto hashTag=hashTagMapper.findByTag(replyHashTag.getTag());
        int register=0;
        if (hashTag==null){
            hashTag=new HashTagDto();
            hashTag.setTag(replyHashTag.getTag());
            register=hashTagMapper.insertOne(hashTag.getTag());
        }
        register+=replyHashTagMapper.insertOne(replyHashTag);
        return register;

    }
    @Override
    public int  register(BoardHashTagDto boardHashTag) {
        HashTagDto hashTag = hashTagMapper.findByTag(boardHashTag.getTag());
        int register = 0;
        if (hashTag == null) {
            hashTag = new HashTagDto();
            hashTag.setTag(boardHashTag.getTag());
            register = hashTagMapper.insertOne(hashTag.getTag());
        }
        register += boardHashTagMapper.insertOne(boardHashTag);
        return register;
    }

    @Override
    public int remove(BoardHashTagDto boardHashTagDto) {
        return 0;
    }

    @Override
    public List<HashTagDto> search(String tag) {
        List<HashTagDto> search=hashTagMapper.findByTagContains(tag);
        return search;
    }

}

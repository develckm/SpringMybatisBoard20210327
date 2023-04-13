package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.BoardHashTagDto;
import com.acorn.springboardteacher.dto.HashTagDto;
import com.acorn.springboardteacher.mapper.BoardHashTagMapper;
import com.acorn.springboardteacher.mapper.HashTagMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HashTagServiceImp implements HashTagService{
    private HashTagMapper hashTagMapper;
    private BoardHashTagMapper boardHashTagMapper;
    @Override
    public List<HashTagDto> search(String tag) {
        List<HashTagDto> search=hashTagMapper.findByTagContains(tag);
        return search;
    }

    @Override
    public int register(BoardHashTagDto boardHashTag) {
        return 0;
    }

    @Override
    public int remove(BoardHashTagDto boardHashTagDto) {
        return 0;
    }
}

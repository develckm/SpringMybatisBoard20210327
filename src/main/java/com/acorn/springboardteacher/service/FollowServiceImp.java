package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.FollowDto;
import com.acorn.springboardteacher.mapper.FollowMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FollowServiceImp implements FollowService{
    private FollowMapper followMapper;
    @Override
    public int remove(FollowDto followDto) {
        int remove=followMapper.deleteByFromIdAndToId(followDto);
        return remove;
    }

    @Override
    public int register(FollowDto followDto) {
        int register=followMapper.insertOne(followDto);
        return register;
    }
}

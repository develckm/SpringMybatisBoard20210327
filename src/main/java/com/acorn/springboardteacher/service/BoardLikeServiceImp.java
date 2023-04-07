package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.BoardLikeDto;
import com.acorn.springboardteacher.dto.LikeStatusCntDto;
import com.acorn.springboardteacher.mapper.BoardLikeMapper;
import com.acorn.springboardteacher.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoardLikeServiceImp implements BoardLikeService{
    private BoardLikeMapper boardLikeMapper;
    private UserMapper userMapper;
    @Override
    public LikeStatusCntDto read(int bId) {
        LikeStatusCntDto read=boardLikeMapper.countStatusByBId(bId);
        return read;
    }

    @Override
    public LikeStatusCntDto read(int bId, String loginUserId) {
        userMapper.setLoginUserId(loginUserId);
        LikeStatusCntDto read=boardLikeMapper.countStatusByBId(bId);
        userMapper.setLoginUserIdNull();
        return read;
    }

    @Override
    public BoardLikeDto detail(int bId, String uId) {
        BoardLikeDto detail=boardLikeMapper.findByBIdAndUId(bId,uId);
        return detail;
    }

    @Override
    public int register(BoardLikeDto like) {
        int register=boardLikeMapper.insertOne(like);
        return register;
    }

    @Override
    public int modify(BoardLikeDto like) {
        int modify=boardLikeMapper.updateOne(like);
        return modify;
    }

    @Override
    public int remove(BoardLikeDto like) {
        int remove=boardLikeMapper.deleteOne(like);
        return remove;
    }
}

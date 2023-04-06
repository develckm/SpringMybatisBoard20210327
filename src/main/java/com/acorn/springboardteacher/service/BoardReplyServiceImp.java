package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.BoardReplyDto;
import com.acorn.springboardteacher.mapper.BoardReplyMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardReplyServiceImp implements BoardReplyService{
    private BoardReplyMapper boardReplyMapper;
    @Override
    public List<BoardReplyDto> list(int bId) {
        List<BoardReplyDto> list=boardReplyMapper.findByBIdAndParentBrIdIsNull(bId);
        return list;
    }

    @Override
    public BoardReplyDto detail(int brId) {
        BoardReplyDto detail=boardReplyMapper.findByBrId(brId);
        return detail;
    }

    @Override
    public int register(BoardReplyDto reply) {
        int register=boardReplyMapper.insertOne(reply);
        return register;
    }

    @Override
    public int modify(BoardReplyDto reply) {
        int modify=boardReplyMapper.updateOne(reply);
        return modify;
    }

    @Override
    public int remove(int brId) {
        int remove=boardReplyMapper.deleteOne(brId);
        return remove;
    }
}

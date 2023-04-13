package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.BoardReplyDto;
import com.acorn.springboardteacher.dto.ReplyHashTagDto;
import com.acorn.springboardteacher.mapper.BoardReplyMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardReplyServiceImp implements BoardReplyService{
    private BoardReplyMapper boardReplyMapper;
    private HashTagService hashTagService;
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

    @Transactional
    @Override
    public int register(BoardReplyDto reply, String[] hashTags) {
        int register=boardReplyMapper.insertOne(reply);
        if( hashTags !=null){
            for (String tag : hashTags){
                ReplyHashTagDto replyHashTag=new ReplyHashTagDto();
                replyHashTag.setTag(tag);
                replyHashTag.setBrId(reply.getBrId());
                register+=hashTagService.register(replyHashTag);
            }
        }
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

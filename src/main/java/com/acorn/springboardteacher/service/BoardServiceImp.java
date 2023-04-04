package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.BoardDto;
import com.acorn.springboardteacher.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardServiceImp implements  BoardService{
    private BoardMapper boardMapper;
    @Override
    public List<BoardDto> list() {
        List<BoardDto> list=boardMapper.findAll();
        return list;
    }

    @Override
    @Transactional
    public BoardDto detail(int bId) {
        //dataSource.getConnection().commit()
        boardMapper.updateIncrementViewCountBId(bId);//조회수 올리기
        BoardDto detail=boardMapper.findByBId(bId);
        //예외 : dataSource.getConnection().rollBack()
        return detail;
    }

    @Override
    public int register(BoardDto board) {
        int register=boardMapper.insertOne(board);
        return register;
    }

    @Override
    public int modify(BoardDto board) {
        int modify=boardMapper.updateOne(board);
        return modify;
    }

    @Override
    public int remove(int bId) {
        int remove=boardMapper.deleteOne(bId);
        return remove;
    }
}

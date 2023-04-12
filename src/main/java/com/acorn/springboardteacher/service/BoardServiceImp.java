package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.BoardDto;
import com.acorn.springboardteacher.dto.BoardImgDto;
import com.acorn.springboardteacher.mapper.BoardImgMapper;
import com.acorn.springboardteacher.mapper.BoardMapper;
import com.acorn.springboardteacher.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardServiceImp implements  BoardService{
    private BoardMapper boardMapper;
    private UserMapper userMapper;
    private BoardImgMapper boardImgMapper;

    @Override
    public List<BoardDto> list() {
        List<BoardDto> list=boardMapper.findAll();
        return list;
    }
    @Override
    public List<BoardDto> list(String loginUserId) {
        //List<BoardDto> list=boardMapper.findAll(loginUserId); //서브쿼리로 좋아요 불러오기
        userMapper.setLoginUserId(loginUserId); //로그인한 유저 아이디를 mysql 서버에 변수로 등록
        List<BoardDto> list=boardMapper.findAll(); //지연로딩으로 좋아요 불러오기
        userMapper.setLoginUserIdNull(); //사용이 끝나서 삭제
        return list;
    }

    @Override
    public List<BoardImgDto> imgList(int[] biId) {
        List<BoardImgDto> imgList=null;
        if(biId!=null){
            imgList=new ArrayList<>();
            for (int id : biId){
                BoardImgDto imgDto=boardImgMapper.findByBiId(id);
                imgList.add(imgDto);
            }
        }
        return imgList;
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
    @Transactional
    @Override
    public int register(BoardDto board) {
        //bId 가 null
        int register=0;
        register=boardMapper.insertOne(board);
        //insert 할때 생성되고 그 값을 마이바티스가 파라미터인 board 에 전달
        if (board.getImgs()!=null){
            for(BoardImgDto img : board.getImgs()){
                img.setBId(board.getBId());
                register+=boardImgMapper.insertOne(img);
            }
        }
        return register;
    }
    @Transactional
    @Override
    public int modify(BoardDto board, int[] delImgIds) {
        int modify=boardMapper.updateOne(board);
        if(delImgIds!=null){
            for(int biId : delImgIds){
                modify+=boardImgMapper.deleteOne(biId);
            }
        }
        return modify;
    }

    @Override
    public int remove(int bId) {
        int remove=boardMapper.deleteOne(bId);
        return remove;
    }
}

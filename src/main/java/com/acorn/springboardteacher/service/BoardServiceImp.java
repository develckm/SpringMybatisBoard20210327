package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.*;
import com.acorn.springboardteacher.mapper.*;
import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardServiceImp implements  BoardService{
    private BoardMapper boardMapper;
    private UserMapper userMapper;
    private BoardImgMapper boardImgMapper;
    private BoardHashTagMapper boardHashTagMapper;
    private HashTagMapper hashTagMapper;
    @Override
    public List<BoardDto> list(UserDto loginUser, BoardPageDto pageDto) {
        //List<BoardDto> list=boardMapper.findAll(loginUserId); //서브쿼리로 좋아요 불러오기
        if(loginUser!=null)userMapper.setLoginUserId(loginUser.getUId()); //로그인한 유저 아이디를 mysql 서버에 변수로 등록
        PageHelper.startPage(pageDto.getPageNum(),pageDto.getPageSize(), pageDto.getOrderBy());
        List<BoardDto> list=boardMapper.findAll(pageDto); //지연로딩으로 좋아요 불러오기
        if(loginUser!=null)userMapper.setLoginUserIdNull(); //사용이 끝나서 삭제
        return list;
    }

    @Override
    public List<BoardDto> tagList(String tag, UserDto loginUser, BoardPageDto pageDto) {
        if(loginUser!=null)userMapper.setLoginUserId(loginUser.getUId());
        PageHelper.startPage(pageDto.getPageNum(),pageDto.getPageSize(), pageDto.getOrderBy());
        List<BoardDto> tagList=boardMapper.findByTag(tag);
        if(loginUser!=null)userMapper.setLoginUserIdNull();
        return tagList;
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
    public int register(BoardDto board, List<String> tags) {
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
        if(tags!=null){
            for(String tag:tags){
                HashTagDto hashTag=hashTagMapper.findByTag(tag);
                if(hashTag==null) register+=hashTagMapper.insertOne(tag);
                BoardHashTagDto boardHashTag=new BoardHashTagDto();
                boardHashTag.setBId(board.getBId());
                boardHashTag.setTag(tag);
                register+=boardHashTagMapper.insertOne(boardHashTag);
            }
        }

        return register;
    }
    @Transactional
    @Override
    public int modify(BoardDto board, int[] delImgIds, List<String> tags, List<String> delTags) {
        int modify=boardMapper.updateOne(board);
        if(delImgIds!=null){
            for(int biId : delImgIds){
                modify+=boardImgMapper.deleteOne(biId);
            }
        }
        if(tags!=null){
            for(String tag:tags){
                HashTagDto hashTag=hashTagMapper.findByTag(tag);
                if(hashTag==null) modify+=hashTagMapper.insertOne(tag);
                BoardHashTagDto boardHashTag=new BoardHashTagDto();
                boardHashTag.setBId(board.getBId());
                boardHashTag.setTag(tag);
                modify+=boardHashTagMapper.insertOne(boardHashTag);
            }
        }
        if(delTags!=null){
            for(String tag:delTags){
                BoardHashTagDto boardHashTag=new BoardHashTagDto();
                boardHashTag.setTag(tag);
                boardHashTag.setBId(board.getBId());
                modify+=boardHashTagMapper.deleteOne(boardHashTag);
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

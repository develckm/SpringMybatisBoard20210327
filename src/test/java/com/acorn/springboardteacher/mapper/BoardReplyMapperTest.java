package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.BoardReplyDto;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest //junit 테스를할때 스프링부트가 같이 실행되면서 의존성 주입을 받을 수있다.
class BoardReplyMapperTest {
    @Autowired
    private BoardReplyMapper boardReplyMapper;
    @Test
    void findByBIdAndParentBrIdIsNull() {
        List<BoardReplyDto> boardReplies=boardReplyMapper.findByBIdAndParentBrIdIsNull(4);
        System.out.println("boardReplies = " + boardReplies);
        assertNotNull(boardReplies);
    }

    @Test
    void findByParentBrId() {
        List<BoardReplyDto> replies = boardReplyMapper.findByParentBrId(20);
        System.out.println("replies = " + replies);
        assertNotNull(replies);
    }

    @Test
    void findByBrId() {
        BoardReplyDto boardReply = boardReplyMapper.findByBrId(16);
        System.out.println("boardReply = " + boardReply);
        assertNotNull(boardReply);
    }

    @Test
    void insertUpdateDeleteOne() {
        BoardReplyDto boardReply=new BoardReplyDto();
        boardReply.setBId(6);
        boardReply.setParentBrId(19); //대대댓글
        boardReply.setUId("user08");
        boardReply.setImgPath("테스트대대댓글이미지2");
        boardReply.setContent("user08 19번 대댓글에 작성한 대대댓글!! 우우~~~");
        int insertOne = boardReplyMapper.insertOne(boardReply);
        System.out.println("boardReply = " + boardReply);

        boardReply.setImgPath("수정대댓글이미지~");
        boardReply.setContent("내용을 수정하는 테스트 진행중");

        int updateOne = boardReplyMapper.updateOne(boardReply);

        int deleteOne = boardReplyMapper.deleteOne(boardReply.getBrId());

        assertEquals(insertOne+updateOne+deleteOne,3);
    }

}
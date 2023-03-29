package com.acorn.springboardteacher.mapper;
import com.acorn.springboardteacher.dto.BoardDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
//Junit 은 단위 테스트는 spring 과 는 별개로 실행되기 때문에 객체 주입을 받을 수 없는데
//Spring boot 3.0 부터  @SpringBootTest 테스트에 정의하면 spring 에서 생성하는 객체를 주입 받을 수 있다.
class BoardMapperTest {
    //Junit 은 클래스에 생성자를 정의 할 수 없다. 그래서 생성자 없이 객체를 주입 받는 @Autowired 를 작성해야 한다.
    @Autowired
    private BoardMapper boardMapper;

    @Test
    void findAll() {
        List<BoardDto> boardList=boardMapper.findAll();
        System.out.println("boardList = " + boardList);
        Assertions.assertNotNull(boardList);
    }

    @Test
    void findByBId() {
        BoardDto board = boardMapper.findByBId(10);
        //System.out.println("board = " + board);
        //지연로딩(fetch=lazy) : 호출할 때(트리거(get,toString)) 조회
        System.out.println("board.getReplies() = " + board.getReplies());
        System.out.println("board.getUser() = " + board.getUser());
        System.out.println("board.getImgs() = " + board.getImgs());
        System.out.println("board.getLikes() = " + board.getLikes());
        Assertions.assertNotNull(board);
        //board_imgs 를 collection 조회하세요~
    }

    @Test
    void insertOneAndDeleteOne() {
        BoardDto board=new BoardDto();
        board.setTitle("보드 등록 테스트 안녕!");
        board.setContent("내용입니다.");
        board.setUId("admin");
        int insert=boardMapper.insertOne(board);
        System.out.println("insert = " + insert);
        System.out.println("board = " + board);
        
        int delete=boardMapper.deleteOne(board.getBId());
        System.out.println("delete = " + delete);
        Assertions.assertEquals(insert+delete,2);
    }
    //view 하기 전에 user 로 조인 boardLike join  test~~

    @Test
    void updateOne() {
        BoardDto board=new BoardDto();
        board.setTitle("보드 수정 테스트 안녕!");
        board.setContent("수정 내용 입니다.");
        board.setBId(6);
        int update=boardMapper.updateOne(board);
        Assertions.assertEquals(update,1); //수정 성공
        BoardDto updateBoard=boardMapper.findByBId(6);
        System.out.println("updateBoard = " + updateBoard);
    }

    @Test
    void updateStatusByBId() {
        BoardDto board=new BoardDto();
        board.setBId(6);
        board.setStatus("REPORT");
        int update=boardMapper.updateStatusByBId(board);
        Assertions.assertEquals(update,1); //수정 성공
        BoardDto updateBoard=boardMapper.findByBId(6);
        System.out.println("updateBoard = " + updateBoard);

    }
}
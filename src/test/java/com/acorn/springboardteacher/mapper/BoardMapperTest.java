package com.acorn.springboardteacher.mapper;
import com.acorn.springboardteacher.dto.BoardDto;
import com.acorn.springboardteacher.dto.BoardPageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//Junit 은 단위 테스트는 spring 과 는 별개로 실행되기 때문에 객체 주입을 받을 수 없는데
//Spring boot 3.0 부터  @SpringBootTest 테스트에 정의하면 spring 에서 생성하는 객체를 주입 받을 수 있다.
class BoardMapperTest {
    //Junit 은 클래스에 생성자를 정의 할 수 없다. 그래서 생성자 없이 객체를 주입 받는 @Autowired 를 작성해야 한다.
    @Autowired
    private BoardMapper boardMapper;

    @Test
    void findAll() {
        BoardPageDto pageDto=new BoardPageDto();
        List<BoardDto> boardList=boardMapper.findAll(pageDto);
        System.out.println("boardList = " + boardList);
        assertNotNull(boardList);
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
        assertNotNull(board);
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
        assertEquals(insert+delete,2);
    }
    //view 하기 전에 user 로 조인 boardLike join  test~~

    @Test
    void updateOne() {
        BoardDto board=new BoardDto();
        board.setTitle("보드 수정 테스트 안녕!");
        board.setContent("수정 내용 입니다.");
        board.setBId(6);
        int update=boardMapper.updateOne(board);
        assertEquals(update,1); //수정 성공
        BoardDto updateBoard=boardMapper.findByBId(6);
        System.out.println("updateBoard = " + updateBoard);
    }

    @Test
    void updateStatusByBId() {
        BoardDto board=new BoardDto();
        board.setBId(6);
        board.setStatus("REPORT");
        int update=boardMapper.updateStatusByBId(board);
        assertEquals(update,1); //수정 성공
        BoardDto updateBoard=boardMapper.findByBId(6);
        System.out.println("updateBoard = " + updateBoard);

    }

    @Test
    void updateIncrementViewCountBId() {
        int updateIncrementViewCountBId = boardMapper.updateIncrementViewCountBId(1);
        assertEquals(updateIncrementViewCountBId,1);
    }

    @Test
    void findByTag() {
        List<BoardDto> boards = boardMapper.findByTag("홍대맛집");
        assertNotNull(boards);
    }
}
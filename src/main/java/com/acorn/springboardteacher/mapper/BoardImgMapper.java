package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.BoardImgDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
//점심식사 전까지 BoardImgMapper.xml  만들고 BoardMapper.xml 에서 collection 정의로 ims 불러오기
//BoardImgMapperTest 생성! 5분까지 쉬었다가 정의하세요~
@Mapper
public interface BoardImgMapper {
    //게시글에서 조회되는 이미지 리스트
    //게시글에 이미지 등록
    //게시글의 이미지 삭제 (수정 x)
    List<BoardImgDto> findByBId(int bId);
    BoardImgDto findByBiId(int biId);
    List<BoardImgDto> findByBiIdIn(List<Integer> biIds);
    int insertOne(BoardImgDto boardImg);
    int deleteOne(int biId);
}

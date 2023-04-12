package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.HashTagDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface HashTagMapper {

    //태그검색,태그조회(게시글에 태그 등록전에 태그가 존재하는지 조회),등록,삭제(x),수정(x)
    List<HashTagDto> findByTagContains(String tag);   //where tag like '홍%' => 홍대 홍대입구 홍대맛집 ...
    HashTagDto findByTag(String tag);
    int insertOne(String tag);
}

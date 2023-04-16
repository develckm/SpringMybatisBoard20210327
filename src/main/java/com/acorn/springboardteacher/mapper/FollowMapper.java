package com.acorn.springboardteacher.mapper;

import com.acorn.springboardteacher.dto.FollowDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowMapper {

    //유저가(from) 팔로우하는 유저(to)리스트 (UserMapper)
    //유저를(to) 팔로우하는 유저(from)리스트 (UserMapper)
    //유저가 서로 팔로우하는 유저리스트 (UserMapper)
    //게시글의 글쓴이를 로그인한 유저가 팔로우 했는지?
    //유저가 팔로우 신청
    //유저가 팔로우 취소
    boolean countByFromIdIsILoginUserIdAndToId(String uId);
    int insertOne(FollowDto followDto);
    int deleteOne(FollowDto followDto);
}

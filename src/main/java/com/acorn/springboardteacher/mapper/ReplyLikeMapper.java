package com.acorn.springboardteacher.mapper;
import com.acorn.springboardteacher.dto.LikeStatusCntDto;
import com.acorn.springboardteacher.dto.ReplyLikeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReplyLikeMapper {
    //댓글에 좋아요 싫어요 최고에요 나빠요 수를 반환
    //유저가 댓글에 작성한 좋아요 싫어요 최고에요 나빠요 수를 반환
    //댓글에서 유저가 좋아요 싫어요 최고에요 나빠요 를 등록
    //댓글(br_id)에서 로그인한 유저(u_id)가 좋아요를 했는지 확인??
    //댓글에서 유저가 좋아요를 했다면 싫어요 최고에요 나빠요 로 수정
    //댓글에서 유저가 좋아요를 했다면 좋아요를 취소(삭제)

    ReplyLikeDto findByUidAndBrId(@Param("uId") String uId, @Param("brId") int brId);
    LikeStatusCntDto countStatusByBrId(int brId);
    LikeStatusCntDto countStatusByUId(String uId);
    int insertOne(ReplyLikeDto replyLike);
    int updateOne(ReplyLikeDto replyLike);
    int deleteOne(ReplyLikeDto replyLike);
}

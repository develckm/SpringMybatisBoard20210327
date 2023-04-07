package com.acorn.springboardteacher.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties({"handler"})
//Table board_replies
public class BoardReplyDto {
    private int brId;//pk generate key(==Auto increment)
    @JsonProperty("bId")
    private int bId; //fk boards.b_id
    @JsonProperty("uId")
    private String uId; //fk users.u_id
    private Integer parentBrId;//self join fk board_replies.br_id ISNULL
    private Date postTime; //CURRENT_TIMESTAMP
    private Date updateTime;//CURRENT_TIMESTAMP on update
    private String status;//[PUBLIC,PRIVATE,REPORT,BLOCK]
    private String imgPath;
    private String content;
    private LikeStatusCntDto likes;//board_replies : reply_likes = 1 : N (그렇지만 집계한 결과만 가져옴)
    private List<BoardReplyDto> replies;//대댓글 (리스트로 나자신의 타입을 참조 셀프조인)
    //board_replies : board_replies = 1 : N (self join)
}

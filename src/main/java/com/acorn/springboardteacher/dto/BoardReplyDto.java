package com.acorn.springboardteacher.dto;
import lombok.Data;
import java.util.Date;
@Data
//Table board_replies
public class BoardReplyDto {
    private int brId;//pk generate key(==Auto increment)
    private int bId; //fk boards.b_id
    private String uId; //fk users.u_id
    private Integer parentBrId;//self join fk board_replies.br_id ISNULL
    private Date postTime; //CURRENT_TIMESTAMP
    private Date updateTime;//CURRENT_TIMESTAMP on update
    private String status;//[PUBLIC,PRIVATE,REPORT,BLOCK]
    private String imgPath;
    private String content;
}

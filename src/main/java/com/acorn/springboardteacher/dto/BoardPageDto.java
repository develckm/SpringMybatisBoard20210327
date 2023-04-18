package com.acorn.springboardteacher.dto;

import lombok.Data;

@Data
public class BoardPageDto {

    enum BoardsType {
        b_id, u_id, post_time, update_time, title, content, view_count
    }
    enum DirectType{
        DESC,ASC
    }
    private int pageNum=1;
    private int pageSize=10;
    private BoardsType order=BoardsType.post_time;
    private DirectType direct=DirectType.DESC;
    //**필드명을 받아서 실행하는 것은 sql injection 에 노출 될 수 있음
    //SELECT * FROM board ORDER BY b_id; DROP TABLE boards;
    //SELECT * FROM board ORDER BY "b_id; DROP TABLE boards;" :
    //파라미터를 문자열로 표시하면 sql injection 방지할 수 있는데 order by 는 필드명을 출력해야 하기 때문에 파라미터로 작성 불가능
    private BoardsType searchField;
    private String searchValue;
    private String orderBy;

    public String getOrderBy() {
        //SELECT * FROM ~~ ORDER BY b_id DESC
        //SELECT * FROM ~~ ORDER BY b_idDESC
        //SELECT * FROM ~~ ORDER BY null null
        if(this.order!=null && this.direct!=null){
            return this.order+" "+this.direct;
        }else if(this.order!=null){
            this.direct=DirectType.ASC;
            return this.order+" "+this.direct;
        }
        return BoardsType.update_time+" "+DirectType.DESC;
    }
}
package com.acorn.springboardteacher.dto;

import lombok.Data;

@Data
public class BoardPageDto {
    private int pageNum=1;
    private int pageSize=10;
    private String order="post_time";
    private String direct="DESC";
    //**필드명을 받아서 실행하는 것은 sql injection 에 노출 될 수 있음
    //SELECT * FROM board ORDER BY b_id; DROP TABLE boards;
    //SELECT * FROM board ORDER BY "b_id; DROP TABLE boards;" :
    //파라미터를 문자열로 표시하면 sql injection 방지할 수 있는데 order by 는 필드명을 출력해야 하기 때문에 파라미터로 작성 불가능
    private String searchField;
    private String searchValue;
}

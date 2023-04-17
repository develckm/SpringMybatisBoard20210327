package com.acorn.springboardteacher.dto;

import lombok.Data;

@Data
public class PageDto {
    private int page=1;
    private int offset=10;
    private int startIndex =0;
    private String order="post_time";
    private String direct="DESC";
    private String searchValue;
    private String searchField;
    public PageDto() {}

    public int getStartIndex() {
        this.startIndex=(page-1)*offset;
        return this.startIndex;
    }

    //*default 생성자 없이 생성자를 정의하고 @ModelAttribute 로 사용하면 생성자에서 사용하는 기본형 파라미터를 required=true 로 정의함
//    public PageDto(int page, int offset, String order, String direct, String search) {
//        this.page = page;
//        this.offset = offset;
//        this.order = order;
//        this.direct = direct;
//        this.search = search;
//    }
}

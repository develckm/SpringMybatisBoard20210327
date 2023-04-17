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
    //네비게이션의 수 알기
    private int totalRows;
    private int lastPage;
    private int prevPage;
    private int nextPage;
    private boolean prev;
    private boolean next;

    public PageDto() {}

    public void setSearchValue(String searchValue) {
        if (!searchValue.trim().equals("")) this.searchValue = searchValue;
    }

    public void setSearchField(String searchField) {
        if(!searchField.trim().equals("")) this.searchField = searchField;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        this.lastPage=(int)Math.ceil((double)totalRows/offset);
        this.prevPage=this.page-1;
        this.nextPage=this.page+1;
        this.prev=(this.page>1);
        this.next=(this.page<lastPage);
    }

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

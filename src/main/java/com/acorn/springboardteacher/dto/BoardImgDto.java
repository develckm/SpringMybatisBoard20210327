package com.acorn.springboardteacher.dto;

import lombok.Data;

@Data
public class BoardImgDto {
    private int biId; //pk (Generate key)
    private int bId; //fk board.b_id
    private String imgPath;
}

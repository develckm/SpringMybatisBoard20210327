package com.acorn.springboardteacher.dto;

import lombok.Data;

@Data
public class BoardImgDto {
    private int bi_id; //pk (Generate key)
    private int b_id; //fk board.b_id
    private String img_path;
}

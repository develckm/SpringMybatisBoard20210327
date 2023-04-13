package com.acorn.springboardteacher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HashTagDto {
    private String tag;
    @JsonProperty(value = "bCnt")
    private int bCnt; //fetch.LAZY SELECT COUNT(*) FROM board_hashtags WHERE tag=홍대
}

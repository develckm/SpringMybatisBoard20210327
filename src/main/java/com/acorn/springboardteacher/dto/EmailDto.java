package com.acorn.springboardteacher.dto;

import lombok.Data;

@Data
public class EmailDto {
    private String to;
    private String title;
    private String message;
}

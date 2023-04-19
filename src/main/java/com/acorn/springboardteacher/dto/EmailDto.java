package com.acorn.springboardteacher.dto;

import lombok.Data;

@Data
public class EmailDto {
    // 수신자
    private String to;

    // 제목
    private String subject;

    // 메시지
    private String message;

}

package com.acorn.springboardteacher.controller;


import com.acorn.springboardteacher.dto.HashTagDto;
import com.acorn.springboardteacher.service.HashTagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller : 무조건 view 렌더하거나 or redirect 하는 동적 페이지
//@RestController : 무조건 json or text 을 응답하는 동적페이지 (@ResponseBody)
@RestController
@RequestMapping("/hashtag")
@AllArgsConstructor
public class HashTagController {
    private HashTagService hashTagService;
    @GetMapping("/{tag}/search.do")
    public List<HashTagDto> search(@PathVariable String tag){
        List<HashTagDto> tags=hashTagService.search(tag);
        return tags;
    }
}

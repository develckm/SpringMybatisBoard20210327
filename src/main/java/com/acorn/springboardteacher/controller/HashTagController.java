package com.acorn.springboardteacher.controller;

import com.acorn.springboardteacher.dto.HashTagDto;
import com.acorn.springboardteacher.service.HashTagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/hashtag")
public class HashTagController {
    private HashTagService hashTagService;
    @RequestMapping("/{name}/search.do")
    public @ResponseBody List<HashTagDto> search(@PathVariable String name){
        return hashTagService.searchName(name);
    }
}

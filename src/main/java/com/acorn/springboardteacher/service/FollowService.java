package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.FollowDto;

public interface FollowService {
    int remove(FollowDto followDto);
    int register(FollowDto followDto);
}

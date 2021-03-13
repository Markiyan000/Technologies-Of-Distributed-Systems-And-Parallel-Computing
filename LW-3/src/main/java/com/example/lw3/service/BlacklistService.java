package com.example.lw3.service;

import com.example.lw3.dto.BlacklistDto;
import com.example.lw3.entity.User;

public interface BlacklistService {
    BlacklistDto saveToBlacklist(Long userId);

    boolean isUserInBlacklist(User user);
}

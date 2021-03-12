package com.example.lw3.service;

import com.example.lw3.dto.BlacklistDto;
import com.example.lw3.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface BlacklistService {
    BlacklistDto saveToBlacklist(Long userId);

    boolean isUserInBlacklist(User user);
}

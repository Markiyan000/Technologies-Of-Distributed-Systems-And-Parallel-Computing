package com.example.lw3.service;

import com.example.lw3.dto.BlacklistDto;
import com.example.lw3.entity.User;

import java.util.List;

public interface BlacklistService {
    BlacklistDto saveToBlacklist(Long userId);

    List<BlacklistDto> findAll();

    boolean isUserInBlacklist(User user);

    void deleteByUserId(Long userId);
}

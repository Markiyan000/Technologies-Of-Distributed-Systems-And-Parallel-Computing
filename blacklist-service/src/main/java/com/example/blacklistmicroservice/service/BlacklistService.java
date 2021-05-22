package com.example.blacklistmicroservice.service;

import com.example.blacklistmicroservice.repository.BlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlacklistService {
    @Autowired
    private BlacklistRepository blacklistRepository;

    public boolean isUserInBlacklist(Long userId) {
        return !blacklistRepository.findByUserId(userId).isEmpty();
    }
}

package com.example.blacklistmicroservice.controller;

import com.example.blacklistmicroservice.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blacklist")
public class BlacklistController {
    @Autowired
    private BlacklistService blacklistService;

    @GetMapping("/check")
    public boolean checkIfUserInBlacklist(@RequestParam Long userId) {
        return blacklistService.isUserInBlacklist(userId);
    }
}

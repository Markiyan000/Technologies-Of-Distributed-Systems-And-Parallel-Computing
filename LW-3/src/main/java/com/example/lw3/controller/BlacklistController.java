package com.example.lw3.controller;

import com.example.lw3.dto.BlacklistDto;
import com.example.lw3.service.BlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blacklist")
public class BlacklistController {
    private final BlacklistService blacklistService;

    @PostMapping
    public ResponseEntity<BlacklistDto> saveToBlacklist(@RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(blacklistService.saveToBlacklist(userId));
    }
}

package com.example.lw3.management;

import com.example.lw3.dto.BlacklistDto;
import com.example.lw3.service.BlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<BlacklistDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(blacklistService.findAll());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUserId(@RequestParam Long userId) {
        blacklistService.deleteByUserId(userId);
    }
}

package com.example.lw3.service.impl;

import com.example.lw3.dto.BlacklistDto;
import com.example.lw3.entity.Blacklist;
import com.example.lw3.entity.User;
import com.example.lw3.exception.BlacklistCreationException;
import com.example.lw3.exception.UserNotFoundException;
import com.example.lw3.mapper.BlacklistMapper;
import com.example.lw3.repository.BlacklistRepository;
import com.example.lw3.repository.CustomOrderRepository;
import com.example.lw3.repository.UserRepository;
import com.example.lw3.service.BlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.lw3.messages.Messages.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlacklistServiceImpl implements BlacklistService {
    private final BlacklistRepository blacklistRepository;
    private final UserRepository userRepository;
    private final CustomOrderRepository customOrderRepository;

    @Override
    @Transactional
    public BlacklistDto saveToBlacklist(Long userId) {
        User user =
            userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND + userId));
        BigDecimal userDebt = customOrderRepository.calculateDebtByUserId(userId);
        if (userDebt == null) {
            throw new BlacklistCreationException(String.format(USER_DOES_NOT_HAVE_DEBT, userId));
        }

        Blacklist blacklist = buildBlacklistItem(user, userDebt);
        Blacklist savedBlacklist = blacklistRepository.save(blacklist);

        return BlacklistMapper.toBlacklistDto(savedBlacklist);
    }

    @Override
    public List<BlacklistDto> findAll() {
        List<Blacklist> blacklists = blacklistRepository.findAll();

        return blacklists.stream().map(BlacklistMapper::toBlacklistDto).collect(Collectors.toList());
    }

    @Override
    public boolean isUserInBlacklist(User user) {
        return !blacklistRepository.findByUser(user).isEmpty();
    }

    @Override
    @Transactional
    public void deleteByUserId(Long userId) {
        User foundUser = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND + userId));
        blacklistRepository.deleteByUser(foundUser);
    }

    private Blacklist buildBlacklistItem(User user, BigDecimal userDebt) {
        return Blacklist.builder()
            .creationDate(LocalDateTime.now())
            .user(user)
            .debt(userDebt)
            .build();
    }
}

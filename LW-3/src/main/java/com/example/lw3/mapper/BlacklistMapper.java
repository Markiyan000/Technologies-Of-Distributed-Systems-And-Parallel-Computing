package com.example.lw3.mapper;

import com.example.lw3.dto.BlacklistDto;
import com.example.lw3.entity.Blacklist;

public class BlacklistMapper {
    public static BlacklistDto toBlacklistDto(Blacklist blacklist) {
        return BlacklistDto.builder()
            .id(blacklist.getId())
            .creationDate(blacklist.getCreationDate())
            .debt(blacklist.getDebt())
            .userDto(UserMapper.toUserDto(blacklist.getUser()))
            .build();
    }
}

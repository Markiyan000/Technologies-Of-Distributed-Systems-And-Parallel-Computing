package com.example.blacklistmicroservice.repository;

import com.example.blacklistmicroservice.entity.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
    List<Blacklist> findByUserId(Long userId);
}

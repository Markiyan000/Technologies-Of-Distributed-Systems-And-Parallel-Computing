package com.example.lw3.repository;

import com.example.lw3.entity.Blacklist;
import com.example.lw3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
    List<Blacklist> findByUser(User user);

    void deleteByUser(User user);
}

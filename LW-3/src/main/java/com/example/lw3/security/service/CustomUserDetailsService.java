package com.example.lw3.security.service;

import com.example.lw3.entity.User;
import com.example.lw3.security.model.CustomUserDetails;
import com.example.lw3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        return CustomUserDetails.buildFromRealUser(user);
    }
}

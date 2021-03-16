package com.example.lw3.service.impl;

import com.example.lw3.dto.SignInRequestDto;
import com.example.lw3.dto.SignInResponseDto;
import com.example.lw3.dto.SignUpRequestDto;
import com.example.lw3.dto.UserDto;
import com.example.lw3.entity.Role;
import com.example.lw3.entity.User;
import com.example.lw3.exception.BadPasswordException;
import com.example.lw3.mapper.UserMapper;
import com.example.lw3.messages.Messages;
import com.example.lw3.repository.RoleRepository;
import com.example.lw3.repository.UserRepository;
import com.example.lw3.security.JwtProvider;
import com.example.lw3.security.model.CustomUserDetails;
import com.example.lw3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public UserDto save(SignUpRequestDto signUpRequestDto) {
        User user = UserMapper.toUser(signUpRequestDto);
        provideSecurity(user);
        User savedUser = userRepository.save(user);

        return UserMapper.toUserDto(savedUser);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        User user = userRepository.findByEmail(signInRequestDto.getEmail());
        if (!passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword())) {
            throw new BadPasswordException(String.format(Messages.USER_ENTERED_BAD_PASSWORD,
                signInRequestDto.getEmail()));
        }
        String token = jwtProvider.generateToken(user.getEmail());

        return new SignInResponseDto(token);
    }

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal instanceof CustomUserDetails ? ((CustomUserDetails)principal).getUsername() : principal.toString();
        return findByEmail(username);
    }

    private void provideSecurity(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        user.getRoles().add(role);
        role.getUsers().add(user);

        String securedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(securedPassword);
    }
}

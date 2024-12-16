package org.app.hw14_dev.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.app.hw14_dev.mapper.UserMapper;
import org.app.hw14_dev.model.User;
import org.app.hw14_dev.model.dto.request.UserRequest;
import org.app.hw14_dev.model.dto.response.UserResponse;
import org.app.hw14_dev.repository.RoleRepository;
import org.app.hw14_dev.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toUserResponse(user);
    }

    public String createUser(UserRequest userRequest) {
        if (userRepository.existsByUserName(userRequest.getUsername())) {
            return "User already exists";
        }
        User user = User.builder()
                .userName(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .enabled(Boolean.TRUE)
                .build();
        roleRepository.findByName("ROLE_USER")
                .ifPresent(user::addRole);
        userRepository.save(user);
        return "User created";
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public String authenticateUser(UserRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }
}

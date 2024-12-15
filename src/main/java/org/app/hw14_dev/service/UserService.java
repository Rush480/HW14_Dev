package org.app.hw14_dev.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.app.hw14_dev.mapper.UserMapper;
import org.app.hw14_dev.model.User;
import org.app.hw14_dev.model.dto.response.UserResponse;
import org.app.hw14_dev.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toUserResponse(user);
    }

}

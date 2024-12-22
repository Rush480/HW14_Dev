package org.app.hw14_dev.service;

import lombok.AllArgsConstructor;
import org.app.hw14_dev.model.dto.request.UserRequest;
import org.app.hw14_dev.security.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class SecurityService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public boolean isUserMatchesPrincipal(Long userId) {
        return getCurrentUser().getId().equals(userId);
    }

    private CustomUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();
    }

    public String authenticateUser(UserRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }
}

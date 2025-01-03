package org.app.hw14_dev.service;

import lombok.AllArgsConstructor;
import org.app.hw14_dev.exception.DatabaseException;
import org.app.hw14_dev.model.dto.request.UserRequest;
import org.app.hw14_dev.repository.NoteRepository;
import org.app.hw14_dev.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    public boolean isNoteCreatedByPrincipal(Long noteId) {
        Long userId = noteRepository.findById(noteId)
                .orElseThrow(() -> new DatabaseException("Note not found"))
                .getUser()
                .getId();
        return getCurrentUser().getId().equals(userId);
    }

    private CustomUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        Long id = userRepository.findByUserName(principal.getUsername())
                .orElseThrow(() -> new DatabaseException("User not found"))
                .getId();
        return CustomUserDetails.builder()
                .id(id)
                .username(principal.getUsername())
                .password(principal.getPassword())
                .authorities(principal.getAuthorities())
                .build();
    }

    public String authenticateUser(UserRequest userLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }
}

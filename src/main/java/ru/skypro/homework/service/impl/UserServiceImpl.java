package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserDto updateInfoUser(UserDto user, Authentication authentication) {
        return null;
    }
}

package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.UserDto;

public interface UserService {
    UserDto updateInfoUser(UserDto user, Authentication authentication);
}

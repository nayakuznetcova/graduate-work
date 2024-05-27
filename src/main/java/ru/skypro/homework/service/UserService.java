package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.UserDto;

import java.security.Principal;

public interface UserService {
    UserDto updateInfoUser(UserDto user, Principal principal);
}

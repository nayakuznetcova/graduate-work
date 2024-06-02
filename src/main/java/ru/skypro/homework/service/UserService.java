package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.model.special.Role;

import java.io.IOException;
import java.security.Principal;

public interface UserService {
    UserDto updateInfoUser(UserDto user, Principal principal);

    UserEntity getUser(String username);

    boolean isPasswordCorrect(UserEntity user, String currentPassword);

    UserDto setUserPassword(UserEntity user, NewPasswordDto newPasswordDto);

    void createUser(RegisterDto register, Role role);

    void updateAvatarUser(MultipartFile avatarUser, Principal principal) throws IOException;
}

package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;
import java.security.Principal;

public interface UserService {
    UserDto updateInfoUser(UserDto user, Principal principal);
    void updateAvatarUser(MultipartFile avatarUser, Principal principal) throws IOException;
}

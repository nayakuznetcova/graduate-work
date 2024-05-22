package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    @PostMapping("/set_password")
    public ResponseEntity<?> updateUserPassword(@RequestBody NewPasswordDto newPasswordDto,
                                                Authentication authentication) {
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getInfoUser(@RequestBody UserDto user,
                                               Authentication authentication) {
        return null;
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateInfoUser(@RequestBody UserDto user,
                                               Authentication authentication) {
        return null;
    }

    @PatchMapping("/me/image")
    public ResponseEntity<String> updateAvatarUser(
            @RequestParam("image") MultipartFile avatarUser,
            Authentication authentication) {
        return null;
    }
}

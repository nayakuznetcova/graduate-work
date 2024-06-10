package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.controller.swagger.UsersControllerSwagger;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.security.Principal;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController implements UsersControllerSwagger {
    private final UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<?> updateUserPassword(@RequestBody NewPasswordDto newPasswordDto,
                                                Principal principal) {
        UserEntity user = userService.getUser(principal.getName());

        if (!userService.isPasswordCorrect(user, newPasswordDto.getCurrentPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDto updateUserDto = userService.setUserPassword(user, newPasswordDto);
        return ResponseEntity.ok((updateUserDto));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getInfoUser(Principal principal) {
        UserDto userDto = userService.getInfoUser(principal);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateInfoUser(@RequestBody UserDto user, Principal principal) {
        UserDto userDto = userService.updateInfoUser(user, principal);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateAvatarUser(
            @RequestParam("image") MultipartFile avatarUser,
            Principal principal) {
        try {
            userService.updateAvatarUser(avatarUser, principal);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}

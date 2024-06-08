package ru.skypro.homework.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UsersControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Principal principal;

    @Mock
    private MultipartFile avatarUser;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateUserPasswordSuccessTest() {
        NewPasswordDto newPasswordDto = new NewPasswordDto();
        newPasswordDto.setCurrentPassword("oldPassword");
        newPasswordDto.setNewPassword("newPassword");

        UserEntity user = new UserEntity();
        UserDto userDto = new UserDto();

        when(principal.getName()).thenReturn("username");
        when(userService.getUser("username")).thenReturn(user);
        when(userService.isPasswordCorrect(user, "oldPassword")).thenReturn(true);
        when(userService.setUserPassword(user, newPasswordDto)).thenReturn(userDto);

        ResponseEntity<?> response = usersController.updateUserPassword(newPasswordDto, principal);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    void updateUserPasswordFailureTest() {
        NewPasswordDto newPasswordDto = new NewPasswordDto();
        newPasswordDto.setCurrentPassword("wrongPassword");

        UserEntity user = new UserEntity();

        when(principal.getName()).thenReturn("username");
        when(userService.getUser("username")).thenReturn(user);
        when(userService.isPasswordCorrect(user, "wrongPassword")).thenReturn(false);

        ResponseEntity<?> response = usersController.updateUserPassword(newPasswordDto, principal);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void getInfoUserTest() {
        UserDto userDto = new UserDto();

        when(userService.getInfoUser(any(Principal.class))).thenReturn(userDto);

        ResponseEntity<UserDto> response = usersController.getInfoUser(principal);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    void updateInfoUserTest() {
        UserDto user = new UserDto();
        UserDto updatedUserDto = new UserDto();

        when(userService.updateInfoUser(any(UserDto.class), any(Principal.class))).thenReturn(updatedUserDto);

        ResponseEntity<UserDto> response = usersController.updateInfoUser(user, principal);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUserDto, response.getBody());
    }

    @Test
    void updateAvatarUserSuccessTest() throws IOException {
        doNothing().when(userService).updateAvatarUser(any(MultipartFile.class), any(Principal.class));

        ResponseEntity<Void> response = usersController.updateAvatarUser(avatarUser, principal);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateAvatarUserFailureTest() throws IOException {
        doThrow(IOException.class).when(userService).updateAvatarUser(any(MultipartFile.class), any(Principal.class));

        ResponseEntity<Void> response = usersController.updateAvatarUser(avatarUser, principal);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}

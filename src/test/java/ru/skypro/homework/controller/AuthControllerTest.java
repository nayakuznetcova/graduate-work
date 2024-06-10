package ru.skypro.homework.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.model.special.Role;
import ru.skypro.homework.service.AuthService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginSuccessTest() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("user");
        loginDto.setPassword("password");

        when(authService.login("user", "password")).thenReturn(true);

        ResponseEntity<?> response = authController.login(loginDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void loginFailureTest() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("user");
        loginDto.setPassword("wrongpassword");

        when(authService.login("user", "wrongpassword")).thenReturn(false);

        ResponseEntity<?> response = authController.login(loginDto);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void registerSuccessTest() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("user");
        registerDto.setPassword("password");
        registerDto.setRole(Role.USER);

        when(authService.register(any(RegisterDto.class), any(Role.class))).thenReturn(true);

        ResponseEntity<?> response = authController.register(registerDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void registerFailureTest() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("user");
        registerDto.setPassword("password");
        registerDto.setRole(Role.USER);

        when(authService.register(any(RegisterDto.class), any(Role.class))).thenReturn(false);

        ResponseEntity<?> response = authController.register(registerDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void registerDefaultRoleTest() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("user");
        registerDto.setPassword("password");

        when(authService.register(any(RegisterDto.class), any(Role.class))).thenReturn(true);

        ResponseEntity<?> response = authController.register(registerDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
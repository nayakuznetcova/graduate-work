package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Логин - пароль
 */
@Data
public class LoginDto {

    private String username;  // Логин пользователя
    private String password;  // Пароль пользователя
}

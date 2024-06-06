package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.special.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Регистрация
 */
@Data
public class RegisterDto {

    private String username;      // Логин(емайл)
    private String password;      // Пароль
    private String firstName;     // Имя
    private String lastName;      // Фамилия
    private String phone;         // Номер телефона
    @Enumerated(EnumType.STRING)
    private Role role;            // Роль
}

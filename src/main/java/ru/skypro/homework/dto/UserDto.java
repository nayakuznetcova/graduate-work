package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.special.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Пользователь
 */
@Data
public class UserDto {
    private Integer id;               // Идентификатор
    private String email;         // Емайл
    private String firstName;     // Имя
    private String lastName;      // Фамилия
    private String phone;         // Номер телефона
    @Enumerated(EnumType.STRING)
    private Role role;            // Роль
    private String image;         // Аватар
}

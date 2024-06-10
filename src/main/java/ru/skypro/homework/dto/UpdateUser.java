package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Обновление информации о пользователе
 */
@Data
public class UpdateUser {
    private String firstName;  // Имя
    private String lastName;   // Фамилия
    private String phone;      // Номер телефона
}

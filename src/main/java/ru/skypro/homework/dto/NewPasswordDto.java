package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Обновление пароля
 */
@Data
public class NewPasswordDto {
    public String currentPassword; // Текущий пароль
    public String newPassword;     // Новый пароль
}

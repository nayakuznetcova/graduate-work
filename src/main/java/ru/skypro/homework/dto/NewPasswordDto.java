package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NewPasswordDto {
    private String currentPassword;
    private String newPassword;
}

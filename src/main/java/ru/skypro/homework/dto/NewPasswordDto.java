package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NewPasswordDto {
    public String currentPassword;
    public String newPassword;
}

package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateUser {
    private String firstName;
    private String lastName;
    private String phone;
}

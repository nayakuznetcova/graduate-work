package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.special.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Data
public class UserDto {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String image;
}

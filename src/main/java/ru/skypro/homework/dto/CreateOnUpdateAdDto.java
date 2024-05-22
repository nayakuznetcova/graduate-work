package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateOnUpdateAdDto {
    private String title;
    private int price;
    private String description;
}

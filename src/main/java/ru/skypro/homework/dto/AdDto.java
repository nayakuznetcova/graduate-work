package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Объявление
 */
@Data
public class AdDto {
    private int author;     // Идентификатор автора объявления
    private String image;   // Ссылка на фото объявления
    private int pk;         // Идентификатор объявления
    private int price;      // Цена объявления
    private String title;   // Название объявления
}

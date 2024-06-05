package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Создание - изменение объявления
 */
@Data
public class CreateOnUpdateAdDto {
    private String title;        // Название объявления
    private int price;           // Цена объявления
    private String description;  // Описание объявления
}

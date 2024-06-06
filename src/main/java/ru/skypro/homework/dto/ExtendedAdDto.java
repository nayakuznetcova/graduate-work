package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Вся информация о объявлении
 */
@Data
public class ExtendedAdDto {
    private int pk;                  // Идентификатор
    private String authorFirstName;  // Имя автора
    private String authorLastName;   // Фамилия автора
    private String description;      // Описание
    private String email;            // Логин(емайл) автора
    private String image;            // Ссылка на фото объявления
    private String phone;            // Номер телефона автора
    private int price;               // Цена
    private String title;            // Название объявления
}

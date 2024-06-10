package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Создание - изменение комментария
 */
@Data
public class CreateOrUpdateComment {
    private String text;  //Текст комментария
}

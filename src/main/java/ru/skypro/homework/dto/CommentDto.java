package ru.skypro.homework.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * Комментарий
 */
@Data
public class CommentDto {
    private int author;              // Идентификатор автора комментария
    private String authorImage;      // Ссылка на фото автора комментария
    private String authorFirstName;  // Имя автора комментария
    private long createdAt;     // Дата и время создания комментария
    private int pk;                  // Идентификатор комментария
    private String text;             // Текст комментария

}

package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@Data
public class CommentDto {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private LocalDate createdAt;
    private int pk;
    private String text;

}

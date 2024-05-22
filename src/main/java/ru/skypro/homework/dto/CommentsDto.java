package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
public class CommentsDto {
    private int count;
    private List<CommentDto> result;

}

package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
public class AdsDto {
    private int count;
    private List<AdDto> result;
}

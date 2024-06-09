package ru.skypro.homework.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.skypro.homework.model.special.IdentifiedObject;

import javax.persistence.*;

/**
 * Модель фото
 */
@EqualsAndHashCode(exclude = "id", callSuper = false)
@Entity
@Data
@Table(name = "images")
public class ImageEntity extends IdentifiedObject {
    private Long fileSize;     // Размер фото
    private String mediaType;  // Формат фото
    private String path;       // Путь к фото
}

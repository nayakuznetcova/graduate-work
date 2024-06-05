package ru.skypro.homework.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.skypro.homework.model.special.IdentifiedObject;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "images")
public class ImageEntity extends IdentifiedObject {
    private Long fileSize;
    private String mediaType;
    private String path;
}

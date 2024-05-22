package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.model.special.IdentifiedObject;

import javax.persistence.*;

@Entity
@Data
@Table(name = "images")
public class ImageEntity extends IdentifiedObject {
    private Long fileSize;
    private String mediaType;
    private byte[] data;
}

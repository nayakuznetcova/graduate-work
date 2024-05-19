package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.model.special.IdentifiedObject;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "images")
public class ImageEntity extends IdentifiedObject {
}

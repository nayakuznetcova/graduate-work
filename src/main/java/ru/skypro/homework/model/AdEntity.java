package ru.skypro.homework.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.skypro.homework.model.special.IdentifiedObject;

import javax.persistence.*;

/**
 * Модель объявления
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "ads")
public class AdEntity extends IdentifiedObject {
    private int price;           // Цена
    private String title;        // Название
    private String description;  // Описание
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;     // Автор объявления
    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageEntity image;   // Фото объявления
}

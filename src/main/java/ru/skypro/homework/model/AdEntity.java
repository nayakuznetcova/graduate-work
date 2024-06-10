package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.repository.cdi.Eager;
import ru.skypro.homework.model.special.IdentifiedObject;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Модель объявления
 */
@EqualsAndHashCode(exclude = "id", callSuper = false)
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
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageEntity image;   // Фото объявления


    @JsonIgnore
    @OneToMany(mappedBy = "ad")
    private List<CommentEntity> comments; //Комментарии объявления
}

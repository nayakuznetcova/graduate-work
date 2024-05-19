package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.model.special.IdentifiedObject;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ads")
public class AdEntity extends IdentifiedObject {
    private int price;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    @OneToOne
    @JoinColumn(name = "image", referencedColumnName = "id")
    private ImageEntity image;
}

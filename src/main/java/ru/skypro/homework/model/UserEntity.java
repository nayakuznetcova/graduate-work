package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.skypro.homework.model.special.IdentifiedObject;
import ru.skypro.homework.model.special.Role;

import javax.persistence.*;
import java.util.List;

/**
 * Модель пользователя
 */
@EqualsAndHashCode(exclude = "id", callSuper = false)
@Entity
@Data
@Table(name = "users")
public class UserEntity extends IdentifiedObject {
    private String email;      // емайл
    private String username;   // логин
    private String firstName;  // Имя
    private String lastName;   // Фамилия
    private String phone;      // Номер телефона
    private String password;   // Пароль
    @Enumerated(EnumType.STRING)
    private Role role;         // Роль
    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageEntity imageEntity;// Аватарка
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<AdEntity> ads;            // Объявления пользователя
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments;  // Комментарии пользователя

}

package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.model.special.IdentifiedObject;
import ru.skypro.homework.model.special.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity extends IdentifiedObject {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    private ImageEntity image;
    @OneToMany
    private List<AdEntity> ads;
    @OneToMany
    private List<CommentEntity> comments;

}

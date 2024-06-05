package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.skypro.homework.model.special.IdentifiedObject;
import ru.skypro.homework.model.special.Role;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "users")
public class UserEntity extends IdentifiedObject {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageEntity imageEntity;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<AdEntity> ads;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments;

}

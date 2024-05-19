package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.model.special.IdentifiedObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "comments")
public class CommentEntity extends IdentifiedObject {
    private LocalDate createdAt;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}

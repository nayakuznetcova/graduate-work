package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.skypro.homework.model.special.IdentifiedObject;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Модель комментария
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "comments")
public class CommentEntity extends IdentifiedObject {
    private LocalDate createdAt;  // Дата и время создания
    private String text;          // Текст
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;      // Автор комментария


    @ManyToOne
    @JoinColumn(name = "ad_id", referencedColumnName = "id")
    private AdEntity ad;          // Объявление комментария


}

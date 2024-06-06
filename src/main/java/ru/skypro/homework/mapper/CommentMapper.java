package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.UserEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper
public interface CommentMapper {
//    @Mapping(target = "author", source = "user.id")
//    @Mapping(target = "authorImage", source = "user.imageEntity.path")
//    @Mapping(target = "authorFirstName", source = "user.firstName")
//    @Mapping(target = "createdAt", source = "createdAt")
//    @Mapping(target = "pk", ignore = true)
//    @Mapping(target = "text", source = "commentEntity.text")
//    CommentDto toCommentDTO(CommentEntity commentEntity, UserEntity user, LocalDateTime createdAt );



    @Mapping(target = "user", source = "user")
    @Mapping(target = "text", source = "commentDTO.text")
    @Mapping(target = "ad", source = "adEntity")

    CommentEntity toCommentEntity( CommentDto commentDTO, LocalDateTime createdAt, UserEntity user, AdEntity adEntity);


}
//private int author;              // Идентификатор автора комментария
//private String authorImage;      // Ссылка на фото автора комментария
//private String authorFirstName;  // Имя автора комментария
//private LocalDate createdAt;     // Дата и время создания комментария
//private int pk;                  // Идентификатор комментария
//private String text;             // Текст комментария

//private LocalDate createdAt;  // Дата и время создания
//private String text;          // Текст
//private UserEntity user;      // Автор комментария
//private AdEntity ad;
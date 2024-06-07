package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;
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
    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorImage", source = "user.imageEntity.path")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "createdAt", source = "commentEntity.createdAt")
    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "text", source = "commentEntity.text")
    CommentDto toCommentDTO(CommentEntity commentEntity, UserEntity user);
    @Mapping(target = "text", source = "comment.text")
    @Mapping(target = "user", source = "user")
//   @Mapping(target = "ad", source = "adEntity")
   CommentEntity toCommentEntity(CreateOrUpdateComment comment, LocalDateTime createdAt, UserEntity user);

//    private LocalDate createdAt;  // Дата и время создания
//    private String text;          // Текст
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private UserEntity user;      // Автор комментария
//    @ManyToOne
//    @JoinColumn(name = "ad_id", referencedColumnName = "id")
//    private AdEntity ad;          // Объявление комментария


}

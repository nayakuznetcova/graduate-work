package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.model.special.IdentifiedObject;

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
    @Mapping(target = "pk", source = "commentEntity.id")
    @Mapping(target = "text", source = "commentEntity.text")
    CommentDto toCommentDTO(CommentEntity commentEntity, UserEntity user);


    @Mapping(target = "text", source = "comment.text")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "ad", source = "adEntity")
    @Mapping(target = "id", ignore = true)
    CommentEntity toCommentEntity(CreateOrUpdateComment comment, long createdAt, UserEntity user, AdEntity adEntity);



}

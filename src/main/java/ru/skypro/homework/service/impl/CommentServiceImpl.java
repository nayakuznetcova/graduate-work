package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentServise;
import ru.skypro.homework.service.UserService;

import java.security.AuthProvider;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentServise {
    Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);

    private final UserService userService;
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;


    @Override
    public CreateOrUpdateComment addComment(Integer id, CreateOrUpdateComment comment, Principal principal) {

        UserEntity user = userService.getUserFromBd(principal);

        LocalDate createdAt = LocalDate.now();


        AdEntity adEntity = adRepository.findById(id).orElseThrow();

        CommentEntity commentEntity = commentMapper.toCommentEntity(comment, createdAt, user,adEntity);

        commentRepository.save(commentEntity);

        return comment;
    }

    @Override
    public CommentsDto getCommentsByAdId(Integer adId) {

        List<CommentEntity> commentEntityList = adRepository.findById(adId).orElseThrow().getComments();

        List<CommentDto> commentDtoList = commentEntityList.stream()
                .map(commentEntity -> {
                    UserEntity user = commentEntity.getUser();
                    return commentMapper.toCommentDTO(commentEntity, user);
                })
                .collect(Collectors.toList());


        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setResult(commentDtoList);
        commentsDto.setCount(commentDtoList.size());
        return commentsDto;


    }

    @Override
    public CreateOrUpdateComment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow();
        commentEntity.setText(comment.getText());
        commentRepository.save(commentEntity);

        return comment;
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        commentRepository.deleteById(commentId);

    }


}

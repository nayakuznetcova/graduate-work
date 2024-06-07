package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;
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
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentServise {
    Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);
    private final AdsService adsService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    //    private final AuthProvider authProvider;
    private final AdRepository adRepository;


    @Override
    public CreateOrUpdateComment addComment(Integer id, CreateOrUpdateComment comment, Principal principal) {
        logger.info("метод в сервисе addComment " );
        UserEntity user = userService.getUserFromBd(principal);
        logger.info("метод в сервисе addComment "+user );
        LocalDateTime createdAt = LocalDateTime.now();



        AdEntity adEntity = adRepository.findById(id).orElseThrow();
        logger.info("объявление " + adEntity);
        CommentEntity commentEntity = commentMapper.toCommentEntity(comment, createdAt, user);

        commentRepository.save(commentEntity);

        return comment;
    }

    @Override
    public CommentsDto getCommentsByAdId(Integer id) {

        CommentEntity comment = commentRepository.findById(id).orElseThrow();
        logger.info("коммент " + comment);

        UserEntity user = userRepository.findById(comment.getUser().getId()).orElseThrow();
        logger.info("коммент " + user);
        commentMapper.toCommentDTO(comment, user);
        return null;

    }


}

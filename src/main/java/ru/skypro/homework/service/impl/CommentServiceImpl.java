package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentServise;
import ru.skypro.homework.service.UserService;

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


    @Override
    public CommentDto addComment(Integer id, CommentDto commentDTO, Principal principal) {
        UserEntity user = userService.getUserFromBd(principal);
        LocalDateTime createdAt = LocalDateTime.now();
        AdEntity adEntity=null;
        CommentEntity commentEntity = commentMapper.toCommentEntity(commentDTO, createdAt, user, adEntity);
        commentRepository.save(commentEntity);

        return commentDTO;
    }
}

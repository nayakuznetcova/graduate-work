package ru.skypro.homework.service;

import org.springframework.web.bind.annotation.RequestBody;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.AdEntity;

import java.security.Principal;

public interface CommentServise {

    CreateOrUpdateComment addComment(Integer id,
                                     CreateOrUpdateComment comment, Principal principal);

    CommentsDto getCommentsByAdId (Integer id);
    CreateOrUpdateComment updateComment(Integer adId, Integer  commentId,  CreateOrUpdateComment  comment);

}

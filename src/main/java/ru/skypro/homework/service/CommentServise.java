package ru.skypro.homework.service;

import org.springframework.web.bind.annotation.RequestBody;
import ru.skypro.homework.dto.CommentDto;

import java.security.Principal;

public interface CommentServise {

    CommentDto addComment(Integer id,
                          CommentDto commentDTO, Principal principal
    );
}

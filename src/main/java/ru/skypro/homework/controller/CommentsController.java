package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.controller.swagger.CommentsControllerSwagger;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.service.CommentServise;
import ru.skypro.homework.service.impl.CommentServiceImpl;

import java.security.Principal;

import javax.persistence.criteria.CriteriaBuilder;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentsController {
    private final CommentServise commentServise;

    @GetMapping("/{id}/comments")


    public ResponseEntity<String> getCommentsByAdId(@PathVariable int id) {

        String result = commentServise.getCommentsByAdId(id).toString();
        System.out.println("result = " + result);
        return ResponseEntity.ok(result);
    }

//    public ResponseEntity<CommentsDto> getCommentsByAdId(@PathVariable Integer id) {
//        return ResponseEntity.ok(commentServise.getCommentsByAdId(id));
//    }
    @PostMapping("/{id}/comments")
    public ResponseEntity<CreateOrUpdateComment> addComment(@PathVariable Integer id,
                                                            @RequestBody CreateOrUpdateComment createOrUpdateComment
            , Principal principal) {

        CreateOrUpdateComment comment = commentServise.addComment(id, createOrUpdateComment, principal);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer adId,
                                           @PathVariable Integer commentId) {

        commentServise.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CreateOrUpdateComment> updateComment(@PathVariable Integer adId,
                                                               @PathVariable Integer commentId,
                                                               @RequestBody CreateOrUpdateComment comment, Principal principal) {

        return ResponseEntity.ok(commentServise.updateComment(adId, commentId, comment));
    }
}

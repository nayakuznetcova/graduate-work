package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.controller.swagger.CommentsControllerSwagger;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentsController implements CommentsControllerSwagger {
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDto> getCommentsByAdId(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable Long id,
                                                 @RequestBody CommentDto commentDTO,
                                                 Authentication authentication) {
        return null;
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long adId,
                                           @PathVariable Long commentId) {
        return null;
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long adId,
                                                    @PathVariable Long commentId,
                                                    @RequestBody CommentDto commentDTO) {
        return null;
    }
}

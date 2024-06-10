package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.controller.swagger.ImageControllerSwagger;

import java.io.IOException;
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/avatar")
@RequiredArgsConstructor
public class ImageController implements ImageControllerSwagger {
    @Value("${path.image}")
    private String avatarPath;
    private final ImageService imageService;

    @GetMapping(value = "/{path}")
    public ResponseEntity<byte[]> getUrl(@PathVariable String path) {
        try {
            byte[] byteFromFile = imageService.getByteFromFile(path);
            return ResponseEntity.ok(byteFromFile);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

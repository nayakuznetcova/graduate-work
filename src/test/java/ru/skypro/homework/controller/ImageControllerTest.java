package ru.skypro.homework.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ImageControllerTest {

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ImageController imageController;

    @Value("${path.image}")
    private String avatarPath;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUrlSuccessTest() throws IOException {
        String path = "testImage.jpg";
        byte[] expectedBytes = new byte[]{1, 2, 3};

        when(imageService.getByteFromFile(path)).thenReturn(expectedBytes);

        ResponseEntity<byte[]> response = imageController.getUrl(path);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBytes, response.getBody());
    }

    @Test
    void getUrlFailureTest() throws IOException {
        String path = "nonexistentImage.jpg";

        when(imageService.getByteFromFile(path)).thenThrow(IOException.class);

        ResponseEntity<byte[]> response = imageController.getUrl(path);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
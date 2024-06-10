package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.ImageEntity;

import java.io.IOException;

public interface ImageService {
    ImageEntity saveImage(MultipartFile avatarUser, ImageEntity imageEntity) throws IOException;
    ImageEntity saveImage(MultipartFile avatarUser) throws IOException;
    byte[] getByteFromFile(String path) throws IOException;

}

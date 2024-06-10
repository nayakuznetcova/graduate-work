package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    @Value("${path.image}")
    private String pathImage;

    @Override
    public ImageEntity saveImage(MultipartFile multipartFile, ImageEntity imageEntity) throws IOException {
        if (imageEntity == null) {
            imageEntity = new ImageEntity();
        }
            return saveImageInFile(multipartFile, imageEntity);
    }
    @Override
    public ImageEntity saveImage(MultipartFile multipartFile) throws IOException {
        return saveImage(multipartFile, null);
    }
    private ImageEntity saveImageInFile(MultipartFile avatarUser, ImageEntity imageEntity) throws IOException {
        String originalFilename = avatarUser.getOriginalFilename();
        String fileName = UUID.randomUUID()+"."+getExtension(Objects.requireNonNull(originalFilename));
        Path filePath = Path.of(pathImage, fileName);

        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        readAndWriteDirectory(avatarUser, filePath);
        imageEntity.setFileSize(avatarUser.getSize());
        imageEntity.setPath("avatar/"+fileName);// изменили
        imageEntity.setMediaType(avatarUser.getContentType());
        imageRepository.save(imageEntity);
        return imageEntity;

    }

    private static void readAndWriteDirectory(MultipartFile avatarUser, Path filePath) throws IOException {
        try (
                InputStream inputStream = avatarUser.getInputStream();
                OutputStream outputStream = Files.newOutputStream(filePath, StandardOpenOption.CREATE_NEW);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1000);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1000);
        ) {
            bufferedInputStream.transferTo(bufferedOutputStream);
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    @Override
    public byte[] getByteFromFile(String path) throws IOException {
        return Files.readAllBytes(Path.of(pathImage, path));
    }
}

package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ImageService imageService;
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    public UserDto updateInfoUser(UserDto user, Principal principal) {
        UserEntity userFromBd = getUserFromBd(principal);
        userFromBd.setFirstName(user.getFirstName());
        userFromBd.setLastName(user.getLastName());
        userFromBd.setPhone(user.getPhone());
        userRepository.save(userFromBd);
        return userMapper.toUserDto(userFromBd);
    }

    @Override
    public void updateAvatarUser(MultipartFile avatarUser, Principal principal) throws IOException {
        UserEntity userFromBd = getUserFromBd(principal);
        ImageEntity imageEntity = userFromBd.getImageEntity();
        ImageEntity newImageEntity = imageService.saveImage(avatarUser, imageEntity);
        userFromBd.setImageEntity(newImageEntity);
        userRepository.save(userFromBd);
    }

    private UserEntity getUserFromBd(Principal principal) {
        String username = principal.getName();
        UserEntity userFromBd = userRepository.findByUsername(username);
        if (userFromBd == null) {
            userFromBd = new UserEntity();
        }
        return userFromBd;
    }
}

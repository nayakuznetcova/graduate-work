package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Override
    public UserDto updateInfoUser(UserDto user, Principal principal) {
        String username = principal.getName();
        UserEntity userFromBd = userRepository.findByUsername(username);
        if (userFromBd == null){
            userFromBd = new UserEntity();
        }
        userFromBd.setFirstName(user.getFirstName());
        userFromBd.setLastName(user.getLastName());
        userFromBd.setPhone(user.getPhone());
        userRepository.save(userFromBd);
        return userMapper.toUserDto(userFromBd);
    }
}

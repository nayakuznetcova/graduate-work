package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.model.special.Role;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.security.Principal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    public UserDto updateInfoUser(UserDto user, Principal principal) {
        String username = principal.getName();
        UserEntity userFromBd = userRepository.findByUsername(username);
        if (userFromBd == null) {
            userFromBd = new UserEntity();
        }
        userFromBd.setFirstName(user.getFirstName());
        userFromBd.setLastName(user.getLastName());
        userFromBd.setPhone(user.getPhone());
        userRepository.save(userFromBd);
        return userMapper.toUserDto(userFromBd);
    }

    @Override
    public UserEntity getUser(String username) {
        return userRepository.findUserEntityByUsername(username).orElseThrow();
    }

    @Override
    public boolean isPasswordCorrect(UserEntity user, String currentPassword) {
        return passwordEncoder.matches(currentPassword, user.getPassword());
    }

    @Override
    public UserDto setUserPassword(UserEntity user, NewPasswordDto newPasswordDto) {
        user.setPassword(passwordEncoder.encode(newPasswordDto.newPassword));
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Override
    public void createUser(RegisterDto register, Role role) {
        UserEntity user = userMapper.toUserEntity(register);
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setRole(Objects.requireNonNullElse(role, Role.USER));
        userRepository.save(user);
    }
}

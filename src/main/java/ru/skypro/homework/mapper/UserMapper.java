package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.UserEntity;

@Mapper
public interface UserMapper {
    @Mapping(target = "image", source = "image.path")
    UserDto toUserDto(UserEntity userEntity);
    @Mapping(target = "image.path", source = "image")
    UserEntity toUserEntity(UserDto userDto);
    @Mapping(target = "password", ignore = true)
    UserEntity toUserEntity(RegisterDto registerDto);
}

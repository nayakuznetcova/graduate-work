package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.UserEntity;

@Mapper
public interface UserMapper {
    @Mapping(target = "image", source = "imageEntity.path")
    UserDto toUserDto(UserEntity userEntity);
    @Mapping(target = "imageEntity.path", ignore = true)
    UserEntity toUserEntity(UserDto userDto);
    @Mapping(target = "password", ignore = true)
    UserEntity toUserEntity(RegisterDto registerDto);
}

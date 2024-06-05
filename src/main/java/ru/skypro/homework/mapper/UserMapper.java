package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.UserEntity;

/**
 * Интерфейс мапперов
 */
@Mapper
public interface UserMapper {
    /**
     * Сопоставление модели пользователя к DTO пользователя
     *
     * @param userEntity модель пользователя
     * @return результат dto пользователя
     */
    @Mapping(target = "image", source = "imageEntity.path")
    UserDto toUserDto(UserEntity userEntity);

    /**
     * Сопоставление DTO пользователя к модели пользователя
     *
     * @param userDto dto пользователя
     * @return результат модели пользователя
     */
    @Mapping(target = "imageEntity.path", ignore = true)
    UserEntity toUserEntity(UserDto userDto);

    /**
     * Сопоставление регистрации пользователя к модели пользователя
     *
     * @param registerDto dto регистрации
     * @return результат модели пользователя
     */
    @Mapping(target = "password", ignore = true)
    UserEntity toUserEntity(RegisterDto registerDto);
}

package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOnUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.model.UserEntity;

@Mapper
public interface AdMapper {
    @Mapping(target = "price", source = "createOnUpdateAdDto.price")
    @Mapping(target = "title", source = "createOnUpdateAdDto.title")
    @Mapping(target = "description", source = "createOnUpdateAdDto.description")
    @Mapping(target = "user", source = "userEntity")
    @Mapping(target = "image", source = "imageEntity")
    @Mapping(target = "id", ignore = true)
    AdEntity toAdEntity(CreateOnUpdateAdDto createOnUpdateAdDto, UserEntity userEntity, ImageEntity imageEntity);
    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "image", source = "image.path")
    @Mapping(target = "pk", source = "id")
    AdDto toAdDto(AdEntity adEntity);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorLastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "image", source = "image.path")
    ExtendedAdDto toExtendedAdDto(AdEntity adEntity);
}

package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;

import java.util.List;

@Mapper
public interface ExtendedAdMapper {

    @Mapping(target = "image", source = "adEntity.image.path")
    @Mapping(target = "pk", source = "pk")
    @Mapping(target = "authorFirstName", source ="userEntity.firstName" )
    @Mapping(target = "authorLastName", source ="userEntity.lastName" )
    ExtendedAdDto toExtendedAdDto(AdEntity adEntity, Integer pk, UserEntity userEntity);

//
    CreateOnUpdateAdDto toCreateOnUpdateAdDto(AdEntity adEntity);

    AdEntity toAdEntity(CreateOnUpdateAdDto toCreateOnUpdateAdDto);

    @Mapping(target = "image", source = "adEntity.image.path")
    @Mapping(target = "pk", source = "adId")
    @Mapping(target = "author", source = "userId")
    AdDto toAdDto(AdEntity adEntity, Integer userId, Integer adId);


    AdsDto toAdsDto(List<AdEntity> allAd ,int count);

//    @Mapping(target = "imageEntity.path", ignore = true)
//    UserEntity toUserEntity(UserDto userDto);
//    @Mapping(target = "password", ignore = true)
//    UserEntity toUserEntity(RegisterDto registerDto);
}

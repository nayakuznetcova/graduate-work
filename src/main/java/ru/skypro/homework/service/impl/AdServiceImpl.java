//package ru.skypro.homework.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.mapstruct.factory.Mappers;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import ru.skypro.homework.controller.AdsController;
//import ru.skypro.homework.dto.AdDto;
//import ru.skypro.homework.dto.AdsDto;
//import ru.skypro.homework.dto.CreateOnUpdateAdDto;
//import ru.skypro.homework.dto.ExtendedAdDto;
//import ru.skypro.homework.mapper.ExtendedAdMapper;
//import ru.skypro.homework.mapper.UserMapper;
//import ru.skypro.homework.model.AdEntity;
//import ru.skypro.homework.model.ImageEntity;
//import ru.skypro.homework.model.UserEntity;
//import ru.skypro.homework.model.special.IdentifiedObject;
//import ru.skypro.homework.repository.AdRepository;
//import ru.skypro.homework.repository.UserRepository;
//import ru.skypro.homework.service.AdService;
//import ru.skypro.homework.service.ImageService;
//import ru.skypro.homework.service.UserService;
//
//import java.io.IOException;
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class AdServiceImpl implements AdService {
//    private final AdRepository adRepository;
//    private final UserRepository userRepository;
////    private final ExtendedAdDto extendedAdDto;
//    private final ImageService imageService;
//    UserService userService;
//    ExtendedAdMapper extendedAdMapper = Mappers.getMapper(ExtendedAdMapper.class);
//    Logger logger = LoggerFactory.getLogger(AdServiceImpl.class);
//    // удалить по  id
//    @Override
//    public void deleteAd(Integer id) {
//        adRepository.deleteById(id);
//
//    }
//
//    // получить по id
//    @Override
//    public ExtendedAdDto getAdById(Integer id) {
//        AdEntity adEntity = adRepository.findById(id).orElseThrow();
//        Integer adId=adRepository.findIdByTitle(adEntity.getTitle());
//
//        UserEntity userEntity = adEntity.getUser();
//
//
//        return extendedAdMapper.toExtendedAdDto(adEntity,adId, userEntity );
//    }
//
//    // post метод
//    @Override
//    public AdDto createAd(CreateOnUpdateAdDto createOnUpdateAdDto, MultipartFile imageFile, Authentication authentication) throws IOException {
//        logger.info("получаем adEntity из createOnUpdateAdDto");
//        AdEntity adEntity = extendedAdMapper.toAdEntity(createOnUpdateAdDto);//получаем adEntity из createOnUpdateAdDto
//
//        ImageEntity imageEntity = adEntity.getImage();//?
//
//        String username = authentication.getName(); //получаем username из authentication
//        logger.info("получаем username из authentication"+username);
//        logger.info("сохраняем в базе картинку");
//        ImageEntity newImageEntity = imageService.saveImage(imageFile, imageEntity);//сохраненное в базе
//        logger.info("получаем User из базы по authentication");
//
//
//        UserEntity userEntity = userService.getUser(username); //получаем User из базы по authentication
//        logger.info(userEntity.toString());
//
//        adEntity.setImage(newImageEntity);
//        adEntity.setUser(userEntity);
//        logger.info("сохраняем в базу объявление");
//        adRepository.save(adEntity); //сохраняем в базу объявление
//
//        Integer userId = userRepository.findIdByEmail(userEntity.getEmail());// получаем id юзера из базы
//        Integer adId=adRepository.findIdByTitle(adEntity.getTitle());
////        UserEntity userFromBd = getUserFromBd(principal);
////        ImageEntity imageEntity = userFromBd.getImageEntity();
////        ImageEntity newImageEntity = imageService.saveImage(avatarUser, imageEntity);
////        userFromBd.setImageEntity(newImageEntity);
////        userRepository.save(userFromBd);
//        return extendedAdMapper.toAdDto(adEntity, userId, adId); //возвращем дто сохраненного объявления
//    }
//
//    @Override
//    public AdsDto getAllAds() {
//        List<AdEntity> allAd = adRepository.findAll();
//        Integer count = allAd.size();
//        return extendedAdMapper.toAdsDto(allAd,count);
//    }
//
//
//}

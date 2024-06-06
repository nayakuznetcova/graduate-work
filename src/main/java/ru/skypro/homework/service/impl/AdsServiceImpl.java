package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOnUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {
    private final UserService userService;
    private final ImageService imageService;
    private final AdRepository adRepository;

    Logger logger = LoggerFactory.getLogger(AdsService.class);
    AdMapper adMapper = Mappers.getMapper(AdMapper.class);

    @Override
    public AdDto createAd(CreateOnUpdateAdDto createOnUpdateAdDto, MultipartFile imageFile, Principal principal) throws IOException {
        UserEntity userFromBd = userService.getUserFromBd(principal);
        ImageEntity newImageEntity = imageService.saveImage(imageFile);
        AdEntity adEntity = adMapper.toAdEntity(createOnUpdateAdDto, userFromBd, newImageEntity);
        AdEntity ad = adRepository.save(adEntity);
        return adMapper.toAdDto(ad);
    }

    @Override
    public AdsDto getAllAds() {
        logger.info("Метод getAllAds");
        List<AdEntity> adEntities = adRepository.findAll();
        return getAdsDto(adEntities);
    }

    @Override
    public AdsDto getAdsByAuthUser(Principal principal){
        UserEntity userFromBd = userService.getUserFromBd(principal);
        List<AdEntity> allAdsByAuthUser = adRepository.findAllByUser(userFromBd);
        return getAdsDto(allAdsByAuthUser);
    }

    private AdsDto getAdsDto(List<AdEntity> adEntities) {
        List<AdDto> adDtoList = adEntities.stream()
                .map(adEntity -> {
                    AdDto adDto = adMapper.toAdDto(adEntity);
                    adDto.setImage("/" + adEntity.getImage().getPath());
                    return adDto;
                })
                .collect(Collectors.toList());
        AdsDto adsDto = new AdsDto();
        adsDto.setResult(adDtoList);
        adsDto.setCount(adDtoList.size());
        return adsDto;
    }

    //     получить по id не работает
//    @Override
//    public ExtendedAdDto getAdById(Integer id) {
//        logger.info("Метод getAdById");
//        AdEntity adEntity = adRepository.findAdEntityById(id).orElseThrow();
//        logger.info("получение объявления из базы " + adEntity.toString());
//        Integer adId = adRepository.findIdByTitle(adEntity.getTitle());
//
//        UserEntity userEntity = adEntity.getUser();
//
//
//        return adMapper.toExtendedAdDto(adEntity, userEntity);
//    }

//    @Override
//    public void deleteAd(Integer id) {
//        adRepository.deleteById(id);
//
//    }

//    @Override
//    public AdsDto getAdsByAuthUser(Principal principal) {
//        logger.info("метод getAdsByAuthUser" );
//        String userName = principal.getName();
//        logger.info("пользователь " + userName);
//        Integer userId = userService.findIdByUserName(userName);// получаем id юзера из базы
//
//
//        List<AdEntity> adEntities =  adRepository.findAdEntityByUser_id(userId);
//
//        List<AdDto> adDtoList = adEntities.stream()
//                .map(adEntity -> adMapper.toAdDto(adEntity))
//                .collect(Collectors.toList());
//        AdsDto adsDto = new AdsDto();
//        adsDto.setResult(adDtoList);
//        adsDto.setCount(adDtoList.size());
//        return adsDto;
//    }


}

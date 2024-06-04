package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOnUpdateAdDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
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
        List<AdEntity> adEntities = adRepository.findAll();
        List<AdDto> adDtoList = adEntities.stream()
                .map(adEntity -> adMapper.toAdDto(adEntity))
                .collect(Collectors.toList());
        AdsDto adsDto = new AdsDto();
        adsDto.setResult(adDtoList);
        adsDto.setCount(adDtoList.size());
        return adsDto;
    }
}

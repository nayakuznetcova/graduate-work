package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
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
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
        return getAdsDto(adEntities);
    }

    @Override
    public ExtendedAdDto getAdById(int id) {
        AdEntity adEntityFromBd = adRepository.findById(id);
        ExtendedAdDto extendedAdDto = adMapper.toExtendedAdDto(adEntityFromBd);
        extendedAdDto.setImage("/" + adEntityFromBd.getImage().getPath());
        return extendedAdDto;
    }

    @Override
    public void deleteAd(int id, Principal principal) {
        AdEntity adEntity = adRepository.findById(id);
        if (adEntity==null){
            throw new NoSuchElementException();
        }
        adRepository.deleteById(id);
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
        adsDto.setResults(adDtoList);
        adsDto.setCount(adDtoList.size());
        return adsDto;
    }

    @Override
    public AdDto updateAd(int id, CreateOnUpdateAdDto createOnUpdateAdDto) {
        AdEntity adEntity = adRepository.findById(id);
        adEntity.setPrice(createOnUpdateAdDto.getPrice());
        adEntity.setTitle(createOnUpdateAdDto.getTitle());
        adEntity.setDescription(createOnUpdateAdDto.getDescription());
        AdEntity newAdEntity = adRepository.save(adEntity);
        return adMapper.toAdDto(newAdEntity);
    }

    @Override
    public byte[] updateAdImage(int adId, MultipartFile imageFile) throws IOException {
        AdEntity adEntityFromBd = adRepository.findById(adId);
        imageService.saveImage(imageFile, adEntityFromBd.getImage());
        return imageFile.getBytes();
    }
}

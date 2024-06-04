package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOnUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

import java.io.IOException;

public interface AdService {


    void deleteAd(Integer id);
    ExtendedAdDto getAdById (Integer id);
     AdDto createAd( CreateOnUpdateAdDto createOnUpdateAdDto,
                                           MultipartFile imageFile, Authentication authentication
                                          ) throws IOException;

     AdsDto getAllAds();
}
